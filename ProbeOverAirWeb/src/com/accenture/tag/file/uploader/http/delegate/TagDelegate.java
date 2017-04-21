package com.accenture.tag.file.uploader.http.delegate;

import com.accenture.tag.file.upload.bean.CreateWorkorderRequest;
import com.accenture.tag.file.upload.bean.CreateWorkorderResponse;
import com.accenture.tag.file.upload.bean.DownloadResultsRequest;
import com.accenture.tag.file.upload.bean.DownloadResultsResponse;
import com.accenture.tag.file.upload.bean.RestRequest;
import com.accenture.tag.file.upload.bean.RetrieveWorkorderDetailsResponse;
import com.accenture.tag.file.uploader.utility.Constants;
import com.accenture.tag.file.uploader.utility.TagLookupUtility;

public class TagDelegate {
	
	private static TagLookupUtility tagLookUpUtility = new TagLookupUtility();
	
	public CreateWorkorderResponse createWorkorder(CreateWorkorderRequest createWORequest) {
		RestRequest restReq = new RestRequest();
		restReq.setRequestObject(createWORequest);
		restReq.setRestUrl(Constants.CREATE_WORKORDER);
		CreateWorkorderResponse createWOResponse = new CreateWorkorderResponse();
		String createWOReqStr = "";
		createWOReqStr = tagLookUpUtility.invokeBLPostService(restReq);
		if(Constants.API_ERROR.equals(createWOReqStr)){
			createWOResponse.setResultCode(Constants.API_ERROR);
			createWOResponse.setResultDescription(Constants.ERROR_OCCURED);
		}else{
			createWOResponse = (CreateWorkorderResponse)tagLookUpUtility.convertJSonToJavaObject(createWOReqStr, CreateWorkorderResponse.class);
		}
		return createWOResponse;
	}
	
	public RetrieveWorkorderDetailsResponse retrieveWorkorderDetails(){
		RestRequest restReq = new RestRequest();
		RetrieveWorkorderDetailsResponse  retrieveWOResponse = new RetrieveWorkorderDetailsResponse();
		restReq.setRestUrl(Constants.RETRIEVE_WORKORDER_DETAILS);
		System.out.println("Retrieve details URL :: "+Constants.RETRIEVE_WORKORDER_DETAILS);
		String retrieveWODetails = "";
		System.out.println("Retrieve details request :: "+restReq);
		retrieveWODetails =  tagLookUpUtility.invokeBLGetService(restReq);
		if(Constants.API_ERROR.equals(retrieveWODetails)){
			retrieveWOResponse.setResultCode(Constants.API_ERROR);
			retrieveWOResponse.setResultDescription(Constants.ERROR_OCCURED);
		}else{
			retrieveWOResponse = (RetrieveWorkorderDetailsResponse)tagLookUpUtility.convertJSonToJavaObject(retrieveWODetails, RetrieveWorkorderDetailsResponse.class);
		}
		return retrieveWOResponse;
	}

	public DownloadResultsResponse downloadResults(DownloadResultsRequest downReq) {
		DownloadResultsResponse  downloadResponse = new DownloadResultsResponse();
		RestRequest restReq = new RestRequest();
		restReq.setRequestObject(downReq);
		restReq.setRestUrl(Constants.DOWNLOAD_RESULTS);
		String downloadRsponseStr = tagLookUpUtility.invokeBLPostService(restReq);
		if(Constants.API_ERROR.equals(downloadRsponseStr)){
			downloadResponse.setResultCode(Constants.API_ERROR);
			downloadResponse.setResultDescription(Constants.ERROR_OCCURED);
		}else{
			downloadResponse = (DownloadResultsResponse)tagLookUpUtility.convertJSonToJavaObject(downloadRsponseStr, DownloadResultsResponse.class);
		}
		return downloadResponse;
		
	}
	
	
	
	

}
