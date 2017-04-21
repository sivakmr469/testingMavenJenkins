package com.accenture.pota.entity.management.request;

import java.io.Serializable;

public class BLRetrieveDeviceRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	
	private String deviceId;
	
	private String deviceName;
	
	private String agentId;

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the agentId
	 */
	public String getAgentId() {
		return agentId;
	}

	/**
	 * @param agentName the agentId to set
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	

}
