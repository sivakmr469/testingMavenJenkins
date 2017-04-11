
package com.accenture.pota.sdk.http.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.pota.sdk.xml.model.CreateWorkorderRequest;
import com.accenture.pota.sdk.xml.model.CreateWorkorderResponse;
import com.accenture.pota.sdk.xml.model.DownloadResultsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderDetailsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderDeviceDetailsResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderListResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderRequest;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderResponse;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderScriptListRequest;
import com.accenture.pota.sdk.xml.model.RetrieveWorkorderScriptListResponse;
import com.accenture.pota.sdk.xml.model.UploadResultsRequest;
import com.accenture.pota.sdk.xml.model.UploadResultsResponse;
import com.accenture.pota.utils.Device;
import com.accenture.pota.entity.management.request.BLDownloadResultsRequest;
import com.accenture.pota.entity.management.request.BLInsertWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderRequest;
import com.accenture.pota.entity.management.request.BLRetrieveWorkorderScriptListRequest;
import com.accenture.pota.entity.management.request.BLUploadResultsRequest;

@Path("/workorder")
@Stateless
public class WorkOrderRest {
	@EJB
	private TagSdkFacadeLocal tagSdkFacade;

	
	@GET
	@Path("login/{user}/{password}")
	public boolean login(@PathParam("user") String user, @PathParam("password") String password, @Context HttpServletRequest httpRequest) {
		boolean flag = false;
		System.out.println("user :: "+user+"  :: password :"+password);
		if(user.equals("admin") && password.equals("admin")){
			httpRequest.getSession().setAttribute("username", "abc");
			flag = true;
			
		}
		return flag;
	}
	
	@GET
	@Path("validateUser")
	public boolean validateUser(@Context HttpServletRequest httpRequest){
		boolean flag = false;
		String Username = (String) httpRequest.getSession().getAttribute("username");
		if(Username!=null){
			System.out.println(Username);
			flag = true;
		}
		return flag;
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("create")
	public CreateWorkorderResponse create(CreateWorkorderRequest request) {
		CreateWorkorderResponse response = new CreateWorkorderResponse();
		BLInsertWorkorderRequest blRequest = new BLInsertWorkorderRequest();
		blRequest.setFileBytes(request.getFileByte());
		blRequest.setFileName(request.getFileName());
		blRequest.setDescription(request.getDescription());
		blRequest.setName(request.getName());
		List<Device> deviceList = request.getDeviceList();
		blRequest.getDeviceList().addAll(deviceList);
		response = tagSdkFacade.createWorkorder(blRequest);
		return response;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("retrieve")
	public RetrieveWorkorderResponse retrieveWorkorder(RetrieveWorkorderRequest request) {
		RetrieveWorkorderResponse response = new RetrieveWorkorderResponse();
		BLRetrieveWorkorderRequest blRequest = new BLRetrieveWorkorderRequest();
		blRequest.setAgentName(request.getAgentName());
		blRequest.setDeviceName(request.getDeviceName());
		response = tagSdkFacade.retrieveWorkorder(blRequest);
		return response;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("retrieve/details")
	public RetrieveWorkorderDetailsResponse retrieveWorkorderDetails() {
		System.out.println("11");
		RetrieveWorkorderDetailsResponse response = new RetrieveWorkorderDetailsResponse();
		response = tagSdkFacade.retrieveWorkorderDetails();
		return response;
	}
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("retrieveworkorderlist")
	public RetrieveWorkorderListResponse retrieveWorkorderList() {
		System.out.println("22");
		RetrieveWorkorderListResponse response = new RetrieveWorkorderListResponse();
		response = tagSdkFacade.retrieveWorkorderList();
		return response;/*
		RetrieveWorkorderDetailsResponse response = new RetrieveWorkorderDetailsResponse();
		response = tagSdkFacade.retrieveWorkorderDetails();
		return response;*/
	
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("upload/results")
	public UploadResultsResponse uploadResults(UploadResultsRequest request) {
		UploadResultsResponse response = new UploadResultsResponse();
		BLUploadResultsRequest blRequest = new BLUploadResultsRequest();
		blRequest.setAllocationId(request.getAllocationId());
		blRequest.setResultByte(request.getResultByte());
		response = tagSdkFacade.updateResults(blRequest);
		return response;
	}
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("download/results")
	public DownloadResultsResponse downloadResults(UploadResultsRequest request) {
		DownloadResultsResponse response = new DownloadResultsResponse();
		BLDownloadResultsRequest blRequest = new BLDownloadResultsRequest();
		blRequest.setAllocationId(request.getAllocationId());
		response = tagSdkFacade.downloadResults(blRequest);
		return response;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("retrievescriptlist")
	public RetrieveWorkorderScriptListResponse retrieveWorkorderScriptList(RetrieveWorkorderScriptListRequest request) {
		
		RetrieveWorkorderScriptListResponse response = new RetrieveWorkorderScriptListResponse();
		BLRetrieveWorkorderScriptListRequest blRequest=new BLRetrieveWorkorderScriptListRequest();
		blRequest.setWorkorderId(request.getWorkorderID());
		response = tagSdkFacade.retrieveWorkorderScriptList(blRequest);
		return response;
	}

	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("retrieve/devicedetails")
	public RetrieveWorkorderDeviceDetailsResponse retrieveWorkorderDeviceDetails(RetrieveWorkorderScriptListRequest request) {
		
		RetrieveWorkorderDeviceDetailsResponse response = new RetrieveWorkorderDeviceDetailsResponse();
		BLRetrieveWorkorderScriptListRequest blRequest=new BLRetrieveWorkorderScriptListRequest();
		blRequest.setWorkorderId(request.getWorkorderID());
		response = tagSdkFacade.retrieveWorkorderDeviceDetails(blRequest);
		return response;
	}

	
}