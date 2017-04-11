package com.accenture.tag.file.uploader.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import javax.ejb.EJB;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.core.resources.ResourcesPlugin;

import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.dal.request.DalRetrieveWorkorderRequest;
import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.pota.sdk.http.servlet.Zip;
import com.accenture.tag.file.upload.bean.DownloadResultsRequest;
import com.accenture.tag.file.upload.bean.DownloadResultsResponse;
import com.accenture.tag.file.upload.bean.RetrieveWorkorderDetailsResponse;
import com.accenture.tag.file.upload.bean.WorkorderList;
import com.accenture.tag.file.uploader.http.delegate.TagDelegate;
import com.accenture.tag.file.uploader.utility.Constants;
import com.accenture.tag.file.uploader.utility.Utils;

public class RetrieveDetails {

	@EJB
	private static TagSdkFacadeLocal tagSdkFacade;

	private static Map<String, WorkorderList> workorderMap = new HashMap<String, WorkorderList>();
	public static byte[] bFile;

	private static final Map<String, String> STATUS_MAP = new LinkedHashMap<String, String>();
	{
		STATUS_MAP.put("1", "Pending");
		STATUS_MAP.put("2", "Inprogress");
		STATUS_MAP.put("3", "Completed");
	}
	private static final Map<String, Integer> STATUS_COLOR_MAP = new LinkedHashMap<String, Integer>();
	{
		STATUS_COLOR_MAP.put("1", new Integer(3));
		STATUS_COLOR_MAP.put("2", new Integer(10));
		STATUS_COLOR_MAP.put("3", new Integer(6));
	}

	public static Person retrivedtails() {
		Person person = new Person();
		TagDelegate tagDelegate = new TagDelegate();
		RetrieveWorkorderDetailsResponse retrieveWorkorderDetails = tagDelegate.retrieveWorkorderDetails();
		if (retrieveWorkorderDetails != null) {
			processWorkorderDetails(retrieveWorkorderDetails);
			person = showResponse(retrieveWorkorderDetails);
		} else {
			System.out.println("retrieveWorkorderDetails is null ---------Service unavailable");
		}
		return person;

	}

	private static Person showResponse(RetrieveWorkorderDetailsResponse retrieveWorkorderDetails) {
		Person person = new Person();
		List<String> lstworkorder = new ArrayList<String>();
		List<ProbeDetailsBean> probeList = new ArrayList<ProbeDetailsBean>();
		String resultCode = retrieveWorkorderDetails.getResultCode();
		if (Constants.TAG_SUCCESS.equalsIgnoreCase(resultCode)) {
			List<WorkorderList> woList = retrieveWorkorderDetails.getWorkorderList();
			for (final WorkorderList workorderList : woList) {
				ProbeDetailsBean probeBean = new ProbeDetailsBean();

				probeBean.setWorkOrderName(workorderList.getWorkorderName());
				if (!lstworkorder.contains(workorderList.getWorkorderName()))
					lstworkorder.add(workorderList.getWorkorderName());
				System.out.println("Workorder Name :: " + workorderList.getWorkorderName());

				probeBean.setDeviceName(workorderList.getDeviceName());
				probeBean.setWorkOrderID(workorderList.getWorkorderId());
				String statusId = workorderList.getStatusId();
				System.out.println("statusID ::" + statusId);
				probeBean.setStatusID(STATUS_MAP.get(statusId.trim()));
				probeBean.setStatus(STATUS_MAP.get(statusId));
				probeList.add(probeBean);
				if (!statusId.equalsIgnoreCase("3")) {
					// downloadBtn.setEnabled(false);
				}
				String allocationId = getKeyByValue(workorderMap, workorderList);
				System.out.println("allocationId ::" + allocationId);
				if (allocationId != null) {
					TagDelegate tagDelegate = new TagDelegate();
					DownloadResultsRequest downReq = new DownloadResultsRequest();
					downReq.setAllocationId(allocationId);
					System.out.println("download request :: " + downReq);
					DownloadResultsResponse downResp = new DownloadResultsResponse();
					downResp = tagDelegate.downloadResults(downReq);
					if (downResp != null) {
						if (Constants.TAG_SUCCESS.equalsIgnoreCase(downResp.getResultCode())) {
							bFile = downResp.getResultByte();
							byte[] decodeBase64 = Base64.decodeBase64(bFile);
							// DirectoryDialog dirDialog = new
							// DirectoryDialog(Display.getDefault().getActiveShell());
							// String workSpacePath =
							// ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
							// dirDialog.setFilterPath(workSpacePath);
							String filePath = "c:\\Pradnya\\workorder\\results";
							FileOutputStream fileOuputStream = null;
							if (Utils.isNotEmptyNull(filePath)) {
								String filepath = filePath + "\\" + "Results_" + System.currentTimeMillis() + ".txt";
								try {
									System.out.println("Uploading resut to JBOSS");
									fileOuputStream = new FileOutputStream(filepath);
									fileOuputStream.write(decodeBase64);
									fileOuputStream.close();
									System.out.println("Result file was successfully downloaded.");

								} catch (Exception e1) {
									System.out.println("Error occured while downloading results");
									e1.printStackTrace();
								}
							}

						} else {
							System.out.println(downResp.getResultDescription());

						}
					} else {
						System.out.println("Service Unavailable");

					}

				} else {
					System.out.println("Error occured while downloading results");

				}
				// }

				// });
			}
			person.setProbeList(probeList);
			person.setWorkorderList(lstworkorder);

		} else {
			System.out.println(retrieveWorkorderDetails.getResultDescription());

		}

		return person;
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	private static void processWorkorderDetails(RetrieveWorkorderDetailsResponse retrieveWorkorderDetails) {
		List<WorkorderList> workorderList = retrieveWorkorderDetails.getWorkorderList();
		if (!Utils.isListEmpty(workorderList)) {
			for (WorkorderList woList : workorderList) {
				workorderMap.put(woList.getAllocationId(), woList);
			}
		}

	}

	public static Person retrivedtails(String probeName, String refName) throws IOException {
		Person person = new Person();
		ProbeDetailsBean bean = new ProbeDetailsBean();
		List<ProbeDetailsBean> lstProbedetails = new ArrayList<ProbeDetailsBean>();
		TagDelegate tagDelegate = new TagDelegate();
		RetrieveWorkorderDetailsResponse retrieveWorkorderDetails = tagDelegate.retrieveWorkorderDetails();
		if (retrieveWorkorderDetails != null) {
			List<WorkorderList> workorderList = retrieveWorkorderDetails.getWorkorderList();
			if (!Utils.isListEmpty(workorderList)) {
				for (WorkorderList woList : workorderList) {
					workorderMap.put(woList.getAllocationId(), woList);
				}
			}

			List<ProbeDetailsBean> probeList = new ArrayList<ProbeDetailsBean>();
			boolean isFileListReady = false;
			List<String> lstDevices = new ArrayList<String>();
			String deviceName = "";
			if (!Utils.isListEmpty(workorderList)) {
				for (WorkorderList woList : workorderList) {
					probeName = probeName.trim();
					System.out.println("Probe. from DB ::" + woList.getWorkorderName() + ":: size::"
							+ woList.getWorkorderName().length() + "  probeName   ::" + probeName + "  :: Check  ::"
							+ (probeName.equals(woList.getWorkorderName().trim())));
					if (probeName.equalsIgnoreCase(woList.getWorkorderId()))

					{
						System.out.println("Found Probe.................");

						ProbeDetailsBean probeBean = new ProbeDetailsBean();

						String statusId = woList.getStatusId();
						System.out.println("statusID ::" + statusId);
						System.out.println("status ::" + STATUS_MAP.get(statusId));
						probeBean.setDeviceName(woList.getDeviceName());
						probeBean.setStatusID((statusId));
						probeBean.setStatus(STATUS_MAP.get(statusId));
						lstDevices.add(woList.getDeviceName());
						deviceName = woList.getDeviceName();
						System.out.println("Device List :: " + woList.getDeviceName());
						probeList.add(probeBean);
						if (!statusId.equalsIgnoreCase("3")) {
							// downloadBtn.setEnabled(false);
						}
						String allocationId = getKeyByValue(workorderMap, woList);
						System.out.println("allocationId ::" + allocationId);
						probeBean.setAllocationID(allocationId);
						lstProbedetails.add(probeBean);
					}
					bean.setLstdevices(lstDevices);
					person.setProbeList(probeList);
				}

				/*
				 * 
				 * System.out.println("deviceName to retrive reference name :: "
				 * +deviceName); DalRetrieveWorkorderRequest dalRequest = new
				 * DalRetrieveWorkorderRequest();
				 * dalRequest.setDeviceName(deviceName);
				 * dalRequest.setAgentName(probeName); TagDalWorkorderFacade
				 * facade= new TagDalWorkorderFacade(); System.out.println(
				 * "calling retrieveWorkorder"); // DalRetrieveWorkorderResponse
				 * dalResponse = facade.retrieveWorkorder(dalRequest);
				 * 
				 * String
				 * refName=tagSdkFacade.retrieveWorkorderReferenceName(probeName
				 * );
				 */
				System.out.println("refName :: " + refName);

				System.out.println("calling retrieveWorkorder");
				// List<WorkorderDetailBean> woBeanList =
				// dalResponse.getWoBean();
				/*
				 * if(!TagUtils.isListEmpty(woBeanList)){ WorkorderDetailBean
				 * workorderDetailBean = woBeanList.get(0);
				 */
				// if(!isFileListReady)
				// {
				String filePath = "C:\\JBOSS\\jboss-eap-7.0\\bin\\workorder\\";
				///filePath = "C:\\JBOSS\\jboss-eap-7.0\\probes\\";
				filePath ="\\usr\\local\\share\\jboss\\probes";
				filePath= com.accenture.tag.file.uploader.utility.Constants.UPLOAD_FILE_PATH;
				String zipFilePath = filePath + refName;
				zipFilePath = filePath + refName;
				System.out.println("searching file :: " + zipFilePath);
				bean = Zip.unzip(zipFilePath, "");
				isFileListReady = true;
				// }
				// }
				// **************************************************************************************

			}

		} else {
			System.out.println("retrieveWorkorderDetails is null ---------Service unavailable");
		}
		person.setProbeList(lstProbedetails);
		person.setLstFileName(bean.getLstFileName());

		// return bean;
		return person;

	}

	public static List<WorkOrderDetail> retriveDashboardDetails() {
		List<WorkOrderDetail> bean = tagSdkFacade.retriveDashboardDetails();
		return bean;
	}

}
