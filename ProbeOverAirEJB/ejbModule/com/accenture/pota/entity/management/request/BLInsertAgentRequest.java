package com.accenture.pota.entity.management.request;

import java.io.Serializable;

public class BLInsertAgentRequest implements Serializable{

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -1799163998536481972L;
	
	private String agentId;
	
	private String agentName;

	/**
	 * @return the agentId
	 */
	public String getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId the agentId to set
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}

	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	

}
