package com.accenture.pota.entity.management.request;

import java.io.Serializable;

import com.accenture.pota.utils.BLResponse;

public class BLRegisterDeviceAndAgentResponse  extends BLResponse implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 1L;
	private String agentID;
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	

}
