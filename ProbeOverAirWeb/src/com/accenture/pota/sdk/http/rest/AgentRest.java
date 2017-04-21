package com.accenture.pota.sdk.http.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.pota.sdk.xml.model.CreateAgentRequest;
import com.accenture.pota.sdk.xml.model.CreateAgentResponse;
import com.accenture.pota.entity.management.request.BLInsertAgentRequest;

@Path("/agent")
@Stateless 
public class AgentRest  {
		
	  @EJB
	    private TagSdkFacadeLocal agentSdkFacade;
	
	 @POST
	    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Path("create")
	    public CreateAgentResponse createAgent(CreateAgentRequest request) {
		 	CreateAgentResponse response = new CreateAgentResponse();
		 	BLInsertAgentRequest blRequest = new BLInsertAgentRequest();
		 	blRequest.setAgentId(request.getAgentId());
		 	blRequest.setAgentName(request.getName());
			response = agentSdkFacade.createAgent(blRequest);
	        return response;
	    }
}
