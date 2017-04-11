package com.accenture.pota.sdk.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jws.WebMethod;

import com.accenture.pota.utils.Constants;
import com.accenture.pota.utils.TagException;
import com.accenture.tag.file.uploader.utility.RetriveScriptFilesList;
import com.accenture.pota.bean.AgentBeanLocal;
import com.accenture.pota.bean.RegisterDeviceLocal;
import com.accenture.pota.bean.WorkorderLocal;
import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.dal.bean.DeviceDetailsListBean;
import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.model.DeviceDetail;
import com.accenture.pota.dal.model.DeviceDetailHome;
import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;
import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.entity.management.request.BLDownloadResultsRequest;
import com.accenture.pota.entity.management.request.BLInsertAgentRequest;
import com.accenture.pota.entity.management.request.BLInsertWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentResponse;
import com.accenture.pota.entity.management.request.BLRegisterDeviceRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderScriptListRequest;
import com.accenture.pota.entity.management.request.BLUploadResultsRequest;
import com.accenture.pota.entity.management.response.BLDownloadResultsResponse;
import com.accenture.pota.entity.management.response.BLInsertAgentResponse;
import com.accenture.pota.entity.management.response.BLInsertWorkorderResponse;
import com.accenture.pota.entity.management.response.BLRegisterDeviceResponse;
import com.accenture.pota.entity.management.response.BLRetrieveDeviceResponse;
import com.accenture.pota.entity.management.response.BLRetrieveWorkorderDetailsResponse;
import com.accenture.pota.entity.management.response.BLRetrieveWorkorderDeviceDetailsResponse;
import com.accenture.pota.entity.management.response.BLRetrieveWorkorderResponse;
import com.accenture.pota.entity.management.response.BLUploadResultsResponse;
import com.accenture.pota.sdk.http.servlet.Zip;
import com.accenture.pota.sdk.xml.model.CreateAgentResponse;
import com.accenture.pota.sdk.xml.model.CreateWorkorderResponse;
import com.accenture.pota.sdk.xml.model.DeviceDetailsList;
import com.accenture.pota.sdk.xml.model.DeviceList;
import com.accenture.pota.sdk.xml.model.DownloadResultsResponse;
import com.accenture.pota.sdk.xml.model.RegisterDeviceAndAgentResponse;
import com.accenture.pota.sdk.xml.model.RegisterDeviceResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderDetailsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderDeviceDetailsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderListResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderScriptListResponse;
import com.accenture.pota.sdk.xml.model.RetrievedeviceListResponse;
import com.accenture.pota.sdk.xml.model.RetrieveworkorderList;
import com.accenture.pota.sdk.xml.model.ScriptList;
import com.accenture.pota.sdk.xml.model.UploadResultsResponse;
import com.accenture.pota.sdk.xml.model.WorkorderList;


@Stateless(name = "AgentSdkController")
@Local(TagSdkFacadeLocal.class)
public class TagSdkFacade implements TagSdkFacadeLocal{

	@EJB
	private AgentBeanLocal agentBean;
	
	@EJB
	private WorkorderLocal workorderBean;
	
	@EJB
	private RegisterDeviceLocal regDeviceBean;
	
	@WebMethod 
	public CreateAgentResponse createAgent(BLInsertAgentRequest blRequest) {
		CreateAgentResponse  agentResp = new CreateAgentResponse();
		BLInsertAgentResponse blInsertAgentResp = new BLInsertAgentResponse();
		try {
			blInsertAgentResp = agentBean.createAgent(blRequest);
			agentResp.setAgentId(blInsertAgentResp.getAgentId());
			agentResp.setResultCode(blInsertAgentResp.getResultCode());
			agentResp.setResultDescription(blInsertAgentResp.getResultDescription());
			
		} catch (TagException e) {
			agentResp.setResultCode(e.getCode());
			agentResp.setResultDescription(e.getDescription());
		}
		return agentResp;
	}

	@WebMethod 
	public CreateWorkorderResponse createWorkorder(
			BLInsertWorkorderRequest blRequest) {
		CreateWorkorderResponse woResponse = new CreateWorkorderResponse();
		BLInsertWorkorderResponse blInsertWOResp = new BLInsertWorkorderResponse();
		try{
			
			blInsertWOResp = workorderBean.createWorkorder(blRequest);
			woResponse.setResultCode(blInsertWOResp.getResultCode());
			woResponse.setResultDescription(blInsertWOResp.getResultDescription());
		}catch(TagException e){
			woResponse.setResultCode(e.getCode());
			woResponse.setResultDescription(e.getDescription());
			
		}
		return woResponse;
	}
	
	@WebMethod 
	public RegisterDeviceResponse registerDevice(BLRegisterDeviceRequest blRequest) {
		RegisterDeviceResponse regDeviceResponse = new RegisterDeviceResponse();
		BLRegisterDeviceResponse  blRegDeviceResp = new BLRegisterDeviceResponse();
		try{
			blRegDeviceResp = regDeviceBean.registerDevice(blRequest);
			regDeviceResponse.setResultCode(blRegDeviceResp.getResultCode());
			regDeviceResponse.setResultDescription(blRegDeviceResp.getResultDescription());
			
		}catch(TagException e){
			regDeviceResponse.setResultCode(e.getCode());
			regDeviceResponse.setResultDescription(e.getDescription());
		}
		return regDeviceResponse;
	}

	@WebMethod
	public RegisterDeviceAndAgentResponse registerDeviceAndAgent(BLRegisterDeviceAndAgentRequest req)
	{
		RegisterDeviceAndAgentResponse regDeviceResponse=new RegisterDeviceAndAgentResponse();
		BLRegisterDeviceAndAgentResponse  blRegDeviceResp = new BLRegisterDeviceAndAgentResponse();
		try{
			blRegDeviceResp = regDeviceBean.registerDeviceAndAgent(req);
			
			regDeviceResponse.setAgentID(blRegDeviceResp.getAgentID());
			regDeviceResponse.setUpdateNow("Y");regDeviceResponse.setUpdateURL("");
			regDeviceResponse.setAgentVersion("0.1");
			
			regDeviceResponse.setResultCode(blRegDeviceResp.getResultCode());
			regDeviceResponse.setResultDescription(blRegDeviceResp.getResultDescription());
			
		}catch(TagException e){
			regDeviceResponse.setResultCode(e.getCode());
			regDeviceResponse.setResultDescription(e.getDescription());
		}
		return regDeviceResponse;
		
	}
	
	@WebMethod 
	public RetrieveWorkorderResponse retrieveWorkorder(BLRetrieveWorkorderRequest blRequest) {
		RetrieveWorkorderResponse retrieveResponse = new RetrieveWorkorderResponse();
		BLRetrieveWorkorderResponse blResponse = new BLRetrieveWorkorderResponse();
		try{
			blResponse = workorderBean.retrieveWorkorder(blRequest);
			byte[] fileByte = blResponse.getFileByte();
			retrieveResponse.setFileByte(fileByte);
			retrieveResponse.setWorkorderId(blResponse.getWorkorderId());
			retrieveResponse.setWorkorderName(blResponse.getWorkorderName());
			retrieveResponse.setAllocationId(blResponse.getAllocationId());
			retrieveResponse.setResultCode(blResponse.getResultCode());
			retrieveResponse.setResultDescription(blResponse.getResultDescription());
		}catch(TagException e){
			retrieveResponse.setResultCode(e.getCode());
			retrieveResponse.setResultDescription(e.getDescription());
		}
		
		return retrieveResponse;
	}


	@WebMethod
	public RetrieveWorkorderDetailsResponse retrieveWorkorderDetails() {
		RetrieveWorkorderDetailsResponse retrieveResponse = new RetrieveWorkorderDetailsResponse();
		BLRetrieveWorkorderDetailsResponse blResponse = new BLRetrieveWorkorderDetailsResponse();
		try{
			blResponse = workorderBean.retrieveWorkorderDetails();
			retrieveResponse.getWorkorderList().addAll(blResponse.getWorkorderList());
			retrieveResponse.setResultCode(blResponse.getResultCode());
			retrieveResponse.setResultDescription(blResponse.getResultDescription());
		}catch(TagException e){
			retrieveResponse.setResultCode(e.getCode());
			retrieveResponse.setResultDescription(e.getDescription());
		}
		return retrieveResponse;
	}

	@WebMethod 
	public UploadResultsResponse updateResults(BLUploadResultsRequest blRequest) {
		UploadResultsResponse uploadResponse = new UploadResultsResponse();
		BLUploadResultsResponse blResponse = new BLUploadResultsResponse();
		try{
			blResponse = workorderBean.updateResults(blRequest);
			uploadResponse.setResultCode(blResponse.getResultCode());
			uploadResponse.setResultDescription(blResponse.getResultDescription());
			
		}catch(TagException e){
			uploadResponse.setResultCode(e.getCode());
			uploadResponse.setResultDescription(e.getDescription());
		}
		return uploadResponse;
	}

	@WebMethod 
	public DownloadResultsResponse downloadResults(BLDownloadResultsRequest blRequest) {
		DownloadResultsResponse downloadResponse = new DownloadResultsResponse();
		BLDownloadResultsResponse blResponse = new BLDownloadResultsResponse();
		try{
			blResponse = workorderBean.downloadResults(blRequest);
			downloadResponse.setResultByte(blResponse.getResultByte());
			downloadResponse.setResultCode(blResponse.getResultCode());
			downloadResponse.setResultDescription(blResponse.getResultDescription());
			
		}catch(TagException e){
			downloadResponse.setResultCode(e.getCode());
			downloadResponse.setResultDescription(e.getDescription());
		}
		return downloadResponse;
	}

	@WebMethod
	public RetrievedeviceListResponse retrieveDeviceList()
			throws TagDalException {
		RetrievedeviceListResponse regDeviceResponse = new RetrievedeviceListResponse();
			BLRetrieveDeviceResponse  blRegDeviceResp = new BLRetrieveDeviceResponse();
			List<DalDeviceDetail> devlist=new ArrayList<DalDeviceDetail>();
			List<DeviceList> lstDevices=new ArrayList<DeviceList>();
			try{
				devlist = regDeviceBean.retrieveDeviceList();
				for(DalDeviceDetail dalDevice: devlist)
				{
					DeviceList device=new DeviceList();
					device.setDeviceId(dalDevice.getDeviceId());
					device.setDeviceName(dalDevice.getDeviceName());
					device.setStatus(dalDevice.getDeviceStatus());
					device.setLocation(dalDevice.getLocation());
					lstDevices.add(device);
				}
				regDeviceResponse.getDeviceList().addAll(lstDevices);
				regDeviceResponse.setResultCode("100");
				regDeviceResponse.setResultDescription("Request successfully executed");
			}catch(TagException e){
				regDeviceResponse.setResultCode(e.getCode());
				regDeviceResponse.setResultDescription(e.getDescription());
			}
			return regDeviceResponse;

//			return dalResponse;
	}

	@WebMethod
	public String retrieveWorkorderReferenceName(String id) {
		// DO Auto-generated method stub
		return workorderBean.retrieveWorkorderReferenceName(id);
	}

	@WebMethod
	public  List<WorkOrderDetail> retriveDashboardDetails() {
		// TODO Auto-generated method stub
		try {
			return workorderBean.retriveDashboardDetails();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@WebMethod
	public RetrieveWorkorderListResponse retrieveWorkorderList() {
		 RetrieveWorkorderListResponse resp=new RetrieveWorkorderListResponse();
		 try
		 {
		 List<WorkOrderDetail>  woList=workorderBean.retriveDashboardDetails();
		 List<RetrieveworkorderList> listWO=new ArrayList<RetrieveworkorderList>();
		 for(WorkOrderDetail detail:woList)
		 {
			 RetrieveworkorderList model=new RetrieveworkorderList();
			 model.setWorkorderId(detail.getWorkorderId());
			 model.setWorkorderName(detail.getName());
			 model.setUploadedBy(detail.getUploadedby());
			 model.setUploadTime(detail.getUploadtime());
			 listWO.add(model);
		 }
		 resp.getRetrieveworkorderList().addAll(listWO);
	
		 resp.setResultCode("100");
		 resp.setResultDescription("Request successfully executed");
	
		 }catch(TagException e){
			 resp.setResultCode(e.getCode());
			 resp.setResultDescription(e.getDescription());
			}
		// TODO Auto-generated method stub
		return resp;
	}

	@Override
	public RetrieveWorkorderScriptListResponse retrieveWorkorderScriptList(
			BLRetrieveWorkorderScriptListRequest blRequest) {
		RetrieveWorkorderScriptListResponse response = new RetrieveWorkorderScriptListResponse();
		List<ScriptList> scriptList = new ArrayList<ScriptList>();
		try {
		String refName = workorderBean.retrieveWorkorderReferenceName(blRequest.getWorkorderId());
		String filePath = "C:\\JBOSS\\jboss-eap-7.0\\bin\\workorder\\";
		//filePath = "C:\\JBOSS\\jboss-eap-7.0\\probes\\";
		filePath = com.accenture.tag.file.uploader.utility.Constants.UPLOAD_FILE_PATH;
		
		String zipFilePath = filePath + refName;
		zipFilePath = filePath + refName;
		System.out.println("searching file :: " + zipFilePath);
		
		scriptList = RetriveScriptFilesList.unzip(zipFilePath, "");
		response.getScriptList().addAll(scriptList);
		response.setResultCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
		response.setResultDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(Message.DAL_WARNING_CODE_RESPONSE);
			response.setResultDescription(Message.DAL_WARNING_DESC_RESPONSE);
			
		}
		return response;
	}

	@Override
	public RetrieveWorkorderDeviceDetailsResponse retrieveWorkorderDeviceDetails(
			BLRetrieveWorkorderScriptListRequest blRequest) {
		RetrieveWorkorderDeviceDetailsResponse response=new RetrieveWorkorderDeviceDetailsResponse();
		BLRetrieveWorkorderDeviceDetailsResponse blResponse=new BLRetrieveWorkorderDeviceDetailsResponse();
		try {
			blResponse=workorderBean.retrieveWorkorderDeviceDetails(blRequest.getWorkorderId());
			List<DeviceDetailsListBean> devicebeanList=blResponse.getDeviceDetailsList();
			List<DeviceDetailsList> deviceDetailsList=new ArrayList<DeviceDetailsList>();
			for(DeviceDetailsListBean bean : devicebeanList)
			{
				DeviceDetailsList devicedetailBean=new DeviceDetailsList();
				devicedetailBean.setDeviceName(bean.getDeviceName());
				devicedetailBean.setLocation(bean.getLocation());
				devicedetailBean.setStatus(bean.getStatus());
				devicedetailBean.setAllocationId(bean.getAllocationId());
				deviceDetailsList.add(devicedetailBean);
				
				
			}
			
			response.getDeviceDetailsList().addAll(deviceDetailsList);
			response.setResultCode(blResponse.getResultCode());
			response.setResultDescription(blResponse.getResultDescription());
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setResultCode(blResponse.getResultCode());
			response.setResultDescription(blResponse.getResultDescription());
		}
		return response;
	}

}
