package com.accenture.pota.entity.management.response;

import java.io.Serializable;

import com.accenture.pota.utils.BLResponse;

public class BLInsertAgentResponse extends BLResponse implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	
	private String name;
	
	private String agentId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

}
