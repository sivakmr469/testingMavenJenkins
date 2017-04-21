package com.accenture.pota.dal.facade.agent;

import javax.ejb.Remote;

import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.request.DalInsertAgentRequest;
import com.accenture.pota.dal.response.DalInsertAgentResponse;
@Remote
public interface TagDalAgentFacadeRemote {

	DalInsertAgentResponse insertAgent(DalInsertAgentRequest dalRequest) throws TagDalException;
}
