package com.accenture.pota.bean;

import java.util.List;

import javax.ejb.Local;

import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.model.WorkOrderDetail;
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
import com.accenture.pota.utils.TagException;

@Local
public interface WorkorderLocal {
	public BLInsertWorkorderResponse createWorkorder(BLInsertWorkorderRequest request) throws TagException;

	public BLRetrieveWorkorderResponse retrieveWorkorder(BLRetrieveWorkorderRequest blRequest) throws TagException;

	public BLRetrieveWorkorderDetailsResponse retrieveWorkorderDetails()throws TagException;
	
	public BLRetrieveWorkorderDeviceDetailsResponse retrieveWorkorderDeviceDetails(String allocationID)throws TagException;

	public BLUploadResultsResponse updateResults(BLUploadResultsRequest blRequest) throws TagException;

	public BLDownloadResultsResponse downloadResults(BLDownloadResultsRequest blRequest) throws TagException;
	
	public String retrieveWorkorderReferenceName(String id);
	
	public  List<WorkOrderDetail> retriveDashboardDetails() throws TagException ;
		
}
