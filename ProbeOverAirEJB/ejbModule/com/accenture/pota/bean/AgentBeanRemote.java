package com.accenture.pota.bean;

import javax.ejb.Remote;

import com.accenture.pota.entity.management.request.BLInsertAgentRequest;
import com.accenture.pota.entity.management.response.BLInsertAgentResponse;
import com.accenture.pota.utils.TagException;

@Remote
public interface AgentBeanRemote {
	public BLInsertAgentResponse createAgent(BLInsertAgentRequest request) throws TagException;
}
