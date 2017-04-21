package com.accenture.pota.sdk.facade;

import com.accenture.pota.entity.management.request.BLInsertAgentRequest;
import com.accenture.pota.sdk.xml.model.CreateAgentResponse;

public interface TagSdkFacadeRemote {

	public CreateAgentResponse createAgent(BLInsertAgentRequest blRequest);
}
