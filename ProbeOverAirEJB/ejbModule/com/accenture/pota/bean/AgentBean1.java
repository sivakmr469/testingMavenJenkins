package com.accenture.pota.bean;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.facade.agent.TagDalAgentFacadeLocal;
import com.accenture.pota.dal.request.DalInsertAgentRequest;
import com.accenture.pota.dal.response.DalInsertAgentResponse;
import com.accenture.pota.entity.management.exception.TagEntityManagementException;
import com.accenture.pota.entity.management.request.BLInsertAgentRequest;
import com.accenture.pota.entity.management.response.BLInsertAgentResponse;
import com.accenture.pota.entity.management.utils.TagEntityManagementMessage;
import com.accenture.pota.utils.TagException;
import com.accenture.pota.utils.TagUtils;

@Stateless
@Local( AgentBeanLocal.class )
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgentBean1 implements AgentBeanLocal {
	
	
	@EJB
	private TagDalAgentFacadeLocal dbAccessAgent;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLInsertAgentResponse createAgent(BLInsertAgentRequest request) throws TagException{
		
		BLInsertAgentResponse  blResponse = new BLInsertAgentResponse();
		String name = request.getAgentName();
		try{
			if( TagUtils.isNull( name ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "agent name" );
			
			DalInsertAgentRequest  dalRequest = new DalInsertAgentRequest();
			dalRequest.setName(name);
			DalInsertAgentResponse dalResponse = dbAccessAgent.insertAgent(dalRequest);
			
			blResponse.setAgentId(dalResponse.getId());
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
		}
		catch (Exception e) {
	
			if (e instanceof TagDalException){
				throw new TagEntityManagementException(((TagDalException) e).getCode(), ((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException){
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(), ((TagEntityManagementException) e).getDescription());
			}
	
		}

		return blResponse;
	}

}
