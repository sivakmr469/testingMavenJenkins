package com.accenture.pota.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.facade.device.TagDalDeviceFacadeLocal;
import com.accenture.pota.dal.facade.workorder.TagDalWorkorderFacadeLocal;
import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.dal.request.DalDownloadResultsRequest;
import com.accenture.pota.dal.request.DalInsertWorkorderRequest;
import com.accenture.pota.dal.request.DalRegisterDeviceRequest;
import com.accenture.pota.dal.request.DalRetrieveWorkorderRequest;
import com.accenture.pota.dal.request.DalUpdateResultsRequest;
import com.accenture.pota.dal.response.DalDownloadResultsResponse;
import com.accenture.pota.dal.response.DalInsertWorkorderResponse;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;
import com.accenture.pota.dal.response.DalRetrieveWordkorderDeviceDetails;
import com.accenture.pota.dal.response.DalRetrieveWorkorderDetailResponse;
import com.accenture.pota.dal.response.DalRetrieveWorkorderResponse;
import com.accenture.pota.dal.response.DalUpdateResultsResponse;
import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.entity.management.exception.TagEntityManagementException;
import com.accenture.pota.entity.management.request.BLDownloadResultsRequest;
import com.accenture.pota.entity.management.request.BLInsertWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderRequest;
import com.accenture.pota.entity.management.request.BLUploadResultsRequest;
import com.accenture.pota.entity.management.response.BLDownloadResultsResponse;
import com.accenture.pota.entity.management.response.BLInsertWorkorderResponse;
import com.accenture.pota.entity.management.response.BLRetrieveWorkorderDetailsResponse;
import com.accenture.pota.entity.management.response.BLRetrieveWorkorderDeviceDetailsResponse;
import com.accenture.pota.entity.management.response.BLRetrieveWorkorderResponse;
import com.accenture.pota.entity.management.response.BLUploadResultsResponse;
import com.accenture.pota.entity.management.utils.TagEntityManagementMessage;
import com.accenture.pota.utils.ConfigHelper;
import com.accenture.pota.utils.Constants;
import com.accenture.pota.utils.Device;
import com.accenture.pota.utils.TagException;
import com.accenture.pota.utils.TagUtils;

@Stateless(name = "WorkorderBLController")
@Local(WorkorderLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class WorkorderBean1 implements WorkorderLocal {

	private final String PROPERTIES_FILE = "tag.properties";

	@EJB
	private TagDalWorkorderFacadeLocal dbAccessWorkorder;

	@EJB
	private TagDalDeviceFacadeLocal dbAccessDevice;

	ConfigHelper configHelper = new ConfigHelper(Constants.PROP_FILE_PATH + PROPERTIES_FILE);

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLInsertWorkorderResponse createWorkorder(BLInsertWorkorderRequest request) throws TagException {
		BLInsertWorkorderResponse blResponse = new BLInsertWorkorderResponse();

		byte[] fileBytes = request.getFileBytes();
		try {
			if (TagUtils.isNull(fileBytes)) {
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "fileBytes");
			}
			String fileName = request.getFileName();
			if (TagUtils.isEmpty(fileName)) {
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "fileName");
			}
			String workorderName = request.getName();
			if (TagUtils.isEmpty(workorderName)) {
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "probe name");
			}

			if (TagUtils.isListEmpty(request.getDeviceList())) {
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "device list");
			}
			DalInsertWorkorderRequest dalRequest = saveFile(request);
			dalRequest.setName(workorderName);
			dalRequest.setDescription(request.getDescription());
			DalInsertWorkorderResponse dalResponse = dbAccessWorkorder.insertWorkorder(dalRequest);

			boolean operationFailed = false;
			if (!Message.DAL_SINGLE_OPERATION_SUCCESS_CODE.equalsIgnoreCase(dalResponse.getCode())) {
				System.out.println("deleting file !!!!!");
				deleteFile(dalRequest);
			} else {
				dalRequest.setId(dalResponse.getId());
				List<Device> devList = request.getDeviceList();
				for (Device device : devList) {
					DalRegisterDeviceRequest dalRetrieveDeviceReq = new DalRegisterDeviceRequest();
					dalRetrieveDeviceReq.setDeviceName(device.getDeviceName());
					DalRetrieveDeviceResponse dalRetrieveDeviceResp = dbAccessDevice
							.retrieveDevice(dalRetrieveDeviceReq);
					if (Message.DAL_SINGLE_OPERATION_SUCCESS_CODE.equalsIgnoreCase(dalRetrieveDeviceResp.getCode())) {
						List<DalDeviceDetail> list = dalRetrieveDeviceResp.getDeviceList();
						if (!TagUtils.isListEmpty(list)) {
							Device dev = new Device();
							dev.setDeviceId(list.get(0).getDeviceId());
							dalRequest.setDevice(dev);
							dalResponse = dbAccessWorkorder.insertWorkorderAllocation(dalRequest);
							if (!Message.DAL_SINGLE_OPERATION_SUCCESS_CODE.equalsIgnoreCase(dalResponse.getCode())) {
								throw new TagEntityManagementException(
										TagEntityManagementMessage.ERROR_WHILE_ALLOCATION_CODE,
										TagEntityManagementMessage.ERROR_WHILE_ALLOCATION_DESC);
							}
						} else {
							operationFailed = true;
						}

					} else {
						operationFailed = true;
					}

				}
				if (operationFailed) {
					throw new TagEntityManagementException(
							TagEntityManagementMessage.DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_CODE,
							TagEntityManagementMessage.DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_DESC);
				}

			}
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());

		} catch (Exception e) {

			if (e instanceof TagDalException) {
				throw new TagEntityManagementException(((TagDalException) e).getCode(),
						((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException) {
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(),
						((TagEntityManagementException) e).getDescription());
			} else {
				blResponse.setResultCode(TagEntityManagementMessage.ERROR_WHILE_ALLOCATION_CODE);
				blResponse.setResultDescription(TagEntityManagementMessage.ERROR_WHILE_ALLOCATION_DESC);
			}
		}
		return blResponse;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLRetrieveWorkorderResponse retrieveWorkorder(BLRetrieveWorkorderRequest blRequest) throws TagException {
		BLRetrieveWorkorderResponse blResponse = new BLRetrieveWorkorderResponse();
		String devName = blRequest.getDeviceName();
		String agentName = blRequest.getAgentName();
		try {
			if (TagUtils.isNull(devName))
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "device name");
			if (TagUtils.isNull(agentName))
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "agent name");
			System.out.println("devName  :: " + devName + "  :: agentName :: " + agentName);
			DalRetrieveWorkorderRequest dalRequest = new DalRetrieveWorkorderRequest();
			dalRequest.setDeviceName(devName);
			dalRequest.setAgentName(agentName);
			DalRetrieveWorkorderResponse dalResponse = dbAccessWorkorder.retrieveWorkorder(dalRequest);
			List<WorkorderDetailBean> woBeanList = dalResponse.getWoBean();
			// System.out.println("woBeanList :: "+woBeanList);
			if (!TagUtils.isListEmpty(woBeanList)) {
				WorkorderDetailBean workorderDetailBean = woBeanList.get(0);
				configHelper = new ConfigHelper(Constants.PROP_FILE_PATH + PROPERTIES_FILE);
				String filePath = configHelper.getString(Constants.WORKORDER_STORAGE_PATH);
				// filePath="C:\\JBOSS\\jboss-eap-7.0\\bin\\null";
				//filePath = "C:\\JBOSS\\jboss-eap-7.0\\probes\\";
				filePath = "/usr/local/share/jboss/probes/";
				
				String zipFilePath = filePath + workorderDetailBean.getReferenceName();
				System.out.println("zipFilePath :: " + zipFilePath);
				File file = new File(zipFilePath);
				file.setReadable(true, false);
				file.setExecutable(true, false);
				file.setWritable(true, false);
				byte[] bFile = new byte[(int) file.length()];
				FileInputStream fileInputStream;
				fileInputStream = new FileInputStream(zipFilePath);
				fileInputStream.read(bFile);
				fileInputStream.close();
				blResponse.setFileByte(bFile);
				blResponse.setWorkorderName(workorderDetailBean.getWorkorderName());
				blResponse.setWorkorderId(workorderDetailBean.getWorkorderId());
				blResponse.setAllocationId(workorderDetailBean.getAllocationId());

				dalRequest.setAllocationId(workorderDetailBean.getAllocationId());
				dalRequest.setStatusId("2");
				dbAccessWorkorder.updateWorkorderStatus(dalRequest);
				
			} else {
				throw new TagEntityManagementException(TagEntityManagementMessage.WORKORDER_NOT_FOUND_CODE,
						TagEntityManagementMessage.WORKORDER_NOT_FOUND_DESC);
			}
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
		} catch (Exception e) {

			if (e instanceof TagDalException) {
				throw new TagEntityManagementException(((TagDalException) e).getCode(),
						((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException) {
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(),
						((TagEntityManagementException) e).getDescription());
			} else {
				e.printStackTrace();
				throw new TagEntityManagementException(TagEntityManagementMessage.WORKORDER_RETRIEVE_CODE,
						TagEntityManagementMessage.WORKORDER_RETRIEVE_DESC);
			}

		}
		return blResponse;
	}

	private void deleteFile(DalInsertWorkorderRequest dalRequest) {
		try {
			configHelper = new ConfigHelper(Constants.PROP_FILE_PATH + PROPERTIES_FILE);
			String filePath = configHelper.getString(Constants.WORKORDER_STORAGE_PATH);
			//filePath = "C:\\JBOSS\\jboss-eap-7.0\\probes\\";
			filePath = "/usr/local/share/jboss/probes/";
			String fileName = dalRequest.getReferenceName();
			File file = new File(filePath + fileName);
			file.setReadable(true, false);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			System.out.println("deleting zip file from  :: " + filePath);
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

		}

	}

	private DalInsertWorkorderRequest saveFile(BLInsertWorkorderRequest request) throws TagEntityManagementException {
		configHelper = new ConfigHelper(Constants.PROP_FILE_PATH + PROPERTIES_FILE);
		String filePath = configHelper.getString(Constants.WORKORDER_STORAGE_PATH);
		//filePath = "C:\\JBOSS\\jboss-eap-7.0\\probes\\";
		filePath = "/usr/local/share/jboss/probes/";
		String fileName = request.getFileName();
		byte[] fileBytes = request.getFileBytes();
		String referenceName = "";
		try {
			String currTimeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			referenceName = currTimeStamp + fileName;
			File workOrderFile = new File(filePath + referenceName);
			workOrderFile.setExecutable(true,false);
			workOrderFile.setReadable(true,false);
			workOrderFile.setWritable(true,false);
			FileOutputStream fos = new FileOutputStream(workOrderFile);
			fos.write(fileBytes);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			throw new TagEntityManagementException(TagEntityManagementMessage.FILE_SAVE_FAILURE_CODE,
					TagEntityManagementMessage.FILE_SAVE_FAILURE_DESC);
		}
		DalInsertWorkorderRequest dalRequest = new DalInsertWorkorderRequest();
		dalRequest.setFileName(fileName);
		dalRequest.setReferenceName(referenceName);
		return dalRequest;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLRetrieveWorkorderDetailsResponse retrieveWorkorderDetails() throws TagException {
		DalRetrieveWorkorderDetailResponse dalResponse = new DalRetrieveWorkorderDetailResponse();
		BLRetrieveWorkorderDetailsResponse bLResponse = new BLRetrieveWorkorderDetailsResponse();
		try {
			dalResponse = dbAccessWorkorder.retrieveWorkorderDetials();
			if (TagUtils.isListEmpty(dalResponse.getWorkorderList())) {
				throw new TagEntityManagementException(TagEntityManagementMessage.WORKORDER_NOT_FOUND_CODE,
						TagEntityManagementMessage.WORKORDER_NOT_FOUND_DESC);
			}
			bLResponse.setResultCode(dalResponse.getCode());
			bLResponse.setResultDescription(dalResponse.getDescription());
			bLResponse.getWorkorderList().addAll(dalResponse.getWorkorderList());
		} catch (Exception e) {
			throw new TagEntityManagementException(TagEntityManagementMessage.WORKORDER_RETRIEVE_CODE,
					TagEntityManagementMessage.WORKORDER_RETRIEVE_DESC);
		}

		return bLResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLUploadResultsResponse updateResults(BLUploadResultsRequest blRequest) throws TagException {
		BLUploadResultsResponse blResponse = new BLUploadResultsResponse();
		DalUpdateResultsResponse dalResponse = new DalUpdateResultsResponse();
		DalUpdateResultsRequest dalRequest = new DalUpdateResultsRequest();
		byte[] fileBytes = blRequest.getResultByte();
		if (TagUtils.isNull(fileBytes)) {
			throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
					TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "result bytes");
		}
		String allId = blRequest.getAllocationId();
		if (TagUtils.isNull(allId)) {
			throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
					TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "allocation Id");
		}
		try {
			dalRequest.setAllocationId(allId);
			dalRequest.setResultByte(fileBytes);
			dalResponse = dbAccessWorkorder.updateWorkorderResults(dalRequest);

			if (!Message.DAL_SINGLE_OPERATION_SUCCESS_CODE.equalsIgnoreCase(dalResponse.getCode())) {
				throw new TagEntityManagementException(TagEntityManagementMessage.UPLOAD_RESULTS_ERROR_CODE,
						TagEntityManagementMessage.UPLOAD_RESULTS_ERROR_DESC);
			}

			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
		} catch (Exception e) {

			if (e instanceof TagDalException) {
				throw new TagEntityManagementException(((TagDalException) e).getCode(),
						((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException) {
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(),
						((TagEntityManagementException) e).getDescription());
			} else {
				blResponse.setResultCode(TagEntityManagementMessage.UPLOAD_RESULTS_ERROR_CODE);
				blResponse.setResultDescription(TagEntityManagementMessage.UPLOAD_RESULTS_ERROR_DESC);
			}
		}
		return blResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLDownloadResultsResponse downloadResults(BLDownloadResultsRequest blRequest) throws TagException {
		BLDownloadResultsResponse blResponse = new BLDownloadResultsResponse();
		DalDownloadResultsResponse dalResponse = new DalDownloadResultsResponse();
		DalDownloadResultsRequest dalRequest = new DalDownloadResultsRequest();
		String allId = blRequest.getAllocationId();
		if (TagUtils.isNull(allId)) {
			throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
					TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "allocation Id");
		}
		try {

			dalRequest.setAllocationId(blRequest.getAllocationId());
			dalResponse = dbAccessWorkorder.retrieveResults(dalRequest);
			if (!Message.DAL_SINGLE_OPERATION_SUCCESS_CODE.equalsIgnoreCase(dalResponse.getCode())) {
				throw new TagEntityManagementException(TagEntityManagementMessage.DOWNLOAD_RESULTS_ERROR_CODE,
						TagEntityManagementMessage.DOWNLOAD_RESULTS_ERROR_DESC);
			}
			blResponse.setResultByte(dalResponse.getResultByte());
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());

		} catch (Exception e) {

			if (e instanceof TagDalException) {
				throw new TagEntityManagementException(((TagDalException) e).getCode(),
						((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException) {
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(),
						((TagEntityManagementException) e).getDescription());
			} else {
				blResponse.setResultCode(TagEntityManagementMessage.DOWNLOAD_RESULTS_ERROR_CODE);
				blResponse.setResultDescription(TagEntityManagementMessage.DOWNLOAD_RESULTS_ERROR_DESC);
			}
		}

		return blResponse;
	}

	@Override
	public String retrieveWorkorderReferenceName(String id) {
		// TODO Auto-generated method stub
		return dbAccessWorkorder.retrieveWorkorderReferenceName(id);
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<WorkOrderDetail> retriveDashboardDetails() {
		// TODO Auto-generated method stub
		return dbAccessWorkorder.retriveDashboardDetails();
	}

	@Override
	public BLRetrieveWorkorderDeviceDetailsResponse retrieveWorkorderDeviceDetails(String workorderId)
			throws TagException {
		BLRetrieveWorkorderDeviceDetailsResponse response = new BLRetrieveWorkorderDeviceDetailsResponse();
		DalRetrieveWordkorderDeviceDetails dalresponse = new DalRetrieveWordkorderDeviceDetails();

		try {
			if (TagUtils.isNull(workorderId) || TagUtils.isEmpty(workorderId))
				throw new TagEntityManagementException(TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "workorder ID");

			dalresponse = dbAccessWorkorder.retrieveWorkorderDeviceDetails(workorderId);
			response.setDeviceDetailsList(dalresponse.getDeviceDetailsList());
			response.setResultCode(dalresponse.getCode());
			response.setResultDescription(dalresponse.getDescription());
		} catch (TagException e) {
			e.printStackTrace();
			response.setResultCode(dalresponse.getCode());
			response.setResultDescription(dalresponse.getDescription());

		}

		return response;
	}

}
