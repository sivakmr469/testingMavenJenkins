package com.accenture.pota.sdk.http.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.pota.sdk.xml.model.RegisterDeviceAndAgentRequest;
import com.accenture.pota.sdk.xml.model.RegisterDeviceAndAgentResponse;
import com.accenture.pota.sdk.xml.model.RegisterDeviceRequest;
import com.accenture.pota.sdk.xml.model.RegisterDeviceResponse;
import com.accenture.pota.sdk.xml.model.RetrievedeviceListResponse;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceRequest;

@Path("/device")
@Stateless
public class DeviceRest {
	 @EJB
	    private TagSdkFacadeLocal tagSdkFacade;
	 
	 @POST
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Path("register")
	    public RegisterDeviceResponse registerDevice(RegisterDeviceRequest request) {
		 	RegisterDeviceResponse response = new RegisterDeviceResponse();
		 	BLRegisterDeviceRequest blRequest = new BLRegisterDeviceRequest();
		 	blRequest.setAgentName(request.getAgentName());
		 	blRequest.setDeviceName(request.getDeviceName());
			response = tagSdkFacade.registerDevice(blRequest);
	        return response;
	    }


	 
	 @POST
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Path("registerdeviceandagent")
	    public RegisterDeviceAndAgentResponse registerDeviceandAgent(RegisterDeviceAndAgentRequest request) {
		 RegisterDeviceAndAgentResponse response = new RegisterDeviceAndAgentResponse();
		 BLRegisterDeviceAndAgentRequest blRequest = new BLRegisterDeviceAndAgentRequest();
		 blRequest.setDeviceName(request.getDeviceName());
		 blRequest.setAgentName(request.getAgentName());
		 blRequest.setLocation(request.getLocation());
		 blRequest.setOS(request.getOS());
		 blRequest.setRAM(request.getRAM());
		 blRequest.setDeviceType(request.getDeviceType());
		 blRequest.setMACID(request.getMACID());
		 blRequest.setDeviceID(request.getMACID());
		 response = tagSdkFacade.registerDeviceAndAgent(blRequest);
	        return response;
	    }

	 	@GET
	    @Produces({MediaType.APPLICATION_JSON})
	    @Path("retriveDeviceList")
	    public RetrievedeviceListResponse retriveDeviceList() {
		RetrievedeviceListResponse response =new RetrievedeviceListResponse();
		try {
			response = tagSdkFacade.retrieveDeviceList();
		} catch (TagDalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return response;
	    }


}
