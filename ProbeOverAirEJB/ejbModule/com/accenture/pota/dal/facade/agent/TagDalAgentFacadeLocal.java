package com.accenture.pota.dal.facade.agent;

import javax.ejb.Local;

import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.request.DalInsertAgentRequest;
import com.accenture.pota.dal.response.DalInsertAgentResponse;
@Local
public interface TagDalAgentFacadeLocal {

	DalInsertAgentResponse insertAgent(DalInsertAgentRequest dalRequest)throws TagDalException;
	

}
