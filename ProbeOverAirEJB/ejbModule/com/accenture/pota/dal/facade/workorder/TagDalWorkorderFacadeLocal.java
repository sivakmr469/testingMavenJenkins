package com.accenture.pota.dal.facade.workorder;

import java.util.List;

import javax.ejb.Local;

import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.dal.request.DalDownloadResultsRequest;
import com.accenture.pota.dal.request.DalInsertWorkorderRequest;
import com.accenture.pota.dal.request.DalRetrieveWorkorderRequest;
import com.accenture.pota.dal.request.DalUpdateResultsRequest;
import com.accenture.pota.dal.response.DalDownloadResultsResponse;
import com.accenture.pota.dal.response.DalInsertWorkorderResponse;
import com.accenture.pota.dal.response.DalRetrieveWordkorderDeviceDetails;
import com.accenture.pota.dal.response.DalRetrieveWorkorderDetailResponse;
import com.accenture.pota.dal.response.DalRetrieveWorkorderResponse;
import com.accenture.pota.dal.response.DalUpdateResultsResponse;

@Local
public interface TagDalWorkorderFacadeLocal {
	DalInsertWorkorderResponse insertWorkorder(DalInsertWorkorderRequest dalRequest)throws TagDalException;

	DalInsertWorkorderResponse insertWorkorderAllocation(DalInsertWorkorderRequest dalRequest)throws TagDalException;

	DalRetrieveWorkorderResponse retrieveWorkorder(DalRetrieveWorkorderRequest dalRequest) throws TagDalException;

	DalRetrieveWorkorderResponse updateWorkorderStatus(DalRetrieveWorkorderRequest dalRequest)throws TagDalException;

	DalRetrieveWorkorderDetailResponse retrieveWorkorderDetials() throws TagDalException;

	DalUpdateResultsResponse updateWorkorderResults(DalUpdateResultsRequest dalRequest)throws TagDalException;

	DalDownloadResultsResponse retrieveResults(DalDownloadResultsRequest dalRequest)throws TagDalException;

	String retrieveWorkorderReferenceName(String id);
	String retrieveWorkordersLastPollTimedOut() throws TagDalException;
	
	List<WorkOrderDetail> retriveDashboardDetails() ;
	DalRetrieveWordkorderDeviceDetails retrieveWorkorderDeviceDetails(String workorderId);
	
}
