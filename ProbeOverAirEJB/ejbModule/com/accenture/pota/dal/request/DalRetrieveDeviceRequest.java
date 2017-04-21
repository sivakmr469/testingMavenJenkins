package com.accenture.pota.dal.request;

import java.io.Serializable;

public class DalRetrieveDeviceRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 3645863515757320461L;

	private String deviceName;
	private String deviceId;
	private String agentName;
	
	public DalRetrieveDeviceRequest() {
		super();
	}
	public DalRetrieveDeviceRequest(String deviceName, String deviceId,
			String agentName) {
		super();
		this.deviceName = deviceName;
		this.deviceId = deviceId;
		this.agentName = agentName;
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
