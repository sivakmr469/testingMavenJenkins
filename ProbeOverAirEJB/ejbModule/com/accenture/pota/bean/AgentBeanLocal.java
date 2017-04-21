package com.accenture.pota.bean;

import javax.ejb.Local;

import com.accenture.pota.entity.management.request.BLInsertAgentRequest;
import com.accenture.pota.entity.management.response.BLInsertAgentResponse;
import com.accenture.pota.utils.TagException;

@Local
public interface AgentBeanLocal {
	public BLInsertAgentResponse createAgent(BLInsertAgentRequest request) throws TagException;

}
