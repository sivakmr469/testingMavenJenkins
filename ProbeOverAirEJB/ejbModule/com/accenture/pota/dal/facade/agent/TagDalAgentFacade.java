package com.accenture.pota.dal.facade.agent;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.model.AgentDetail;
import com.accenture.pota.dal.model.AgentDetailHome;
import com.accenture.pota.dal.request.DalInsertAgentRequest;
import com.accenture.pota.dal.response.DalInsertAgentResponse;
import com.accenture.pota.dal.utils.Message;

@Stateless(name = "AgentDalController")
@Local(TagDalAgentFacadeLocal.class)
public class TagDalAgentFacade implements TagDalAgentFacadeLocal{
	
	@PersistenceContext( unitName = "tagPU" )
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalInsertAgentResponse insertAgent(DalInsertAgentRequest dalRequest)throws TagDalException {
		DalInsertAgentResponse dalResponse = new DalInsertAgentResponse();
		try{
			if( dalRequest == null || dalRequest.getName() == null){
				throw new TagDalException( Message.DAL_MANDATORY_PARAMS_ERROR_CODE,
						   				   Message.DAL_MANDATORY_PARAMS_ERROR_DESC );
			}
			AgentDetailHome agentHome = new AgentDetailHome( entityManager );
			
			AgentDetail agentToInsert = new AgentDetail();
			agentToInsert.setName(dalRequest.getName());
			agentHome.persist( agentToInsert ); 
			
			if(agentToInsert.getAgentId() == null){
				throw new TagDalException(Message.DAL_INSERT_AGENT_GENERIC_ERROR_CODE, 
										  Message.DAL_INSERT_AGENT_GENERIC_ERROR_DESC);
			}
			dalResponse.setId(agentToInsert.getAgentId());
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}catch(PersistenceException pe){
			String causeString = pe.getCause().toString();
			if (causeString != null) 
			{
				if (causeString.contains("org.hibernate.exception.ConstraintViolationException")) 
				{
					throw new TagDalException( Message.DAL_NOT_UNIQUE_VALUE_ERROR_CODE,
							   Message.DAL_NOT_UNIQUE_VALUE_ERROR_DESC + "agent name" );
				} 
			}

		}
		catch(Exception ex){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		return dalResponse;
	}

}
