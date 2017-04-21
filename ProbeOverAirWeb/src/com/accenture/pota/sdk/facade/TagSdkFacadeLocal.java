package com.accenture.pota.sdk.facade;

import java.util.List;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;
import com.accenture.pota.entity.management.request.BLDownloadResultsRequest;
import com.accenture.pota.entity.management.request.BLInsertAgentRequest;
import com.accenture.pota.entity.management.request.BLInsertWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderScriptListRequest;
import com.accenture.pota.entity.management.request.BLUploadResultsRequest;
import com.accenture.pota.sdk.xml.model.CreateAgentResponse;
import com.accenture.pota.sdk.xml.model.CreateWorkorderResponse;
import com.accenture.pota.sdk.xml.model.DownloadResultsResponse;
import com.accenture.pota.sdk.xml.model.RegisterDeviceAndAgentResponse;
import com.accenture.pota.sdk.xml.model.RegisterDeviceResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderDetailsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderDeviceDetailsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderListResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderScriptListResponse;
import com.accenture.pota.sdk.xml.model.RetrievedeviceListResponse;
import com.accenture.pota.sdk.xml.model.UploadResultsResponse;


public interface TagSdkFacadeLocal {

	public CreateAgentResponse createAgent(BLInsertAgentRequest blRequest);

	public CreateWorkorderResponse createWorkorder(BLInsertWorkorderRequest blRequest);

	public RegisterDeviceResponse registerDevice(BLRegisterDeviceRequest blRequest);
	
	public RegisterDeviceAndAgentResponse registerDeviceAndAgent(BLRegisterDeviceAndAgentRequest blRequest);

	public RetrieveWorkorderResponse retrieveWorkorder(BLRetrieveWorkorderRequest blRequest);
	
	public RetrieveWorkorderListResponse retrieveWorkorderList();

	public RetrieveWorkorderScriptListResponse retrieveWorkorderScriptList(BLRetrieveWorkorderScriptListRequest blRequest);
	
	public RetrieveWorkorderDeviceDetailsResponse retrieveWorkorderDeviceDetails(BLRetrieveWorkorderScriptListRequest blRequest);
	
	public RetrieveWorkorderDetailsResponse retrieveWorkorderDetails();

	public UploadResultsResponse updateResults(BLUploadResultsRequest blRequest);

	public DownloadResultsResponse downloadResults(BLDownloadResultsRequest blRequest);
	
	public RetrievedeviceListResponse  retrieveDeviceList() throws TagDalException ;
	
	public String retrieveWorkorderReferenceName(String id);
	
	public  List<WorkOrderDetail> retriveDashboardDetails();
}
