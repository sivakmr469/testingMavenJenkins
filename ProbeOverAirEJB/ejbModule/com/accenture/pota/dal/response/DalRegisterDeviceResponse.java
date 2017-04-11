package com.accenture.pota.dal.response;

public class DalRegisterDeviceResponse extends DalRespo {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -6862696502117306180L;
	private String deviceId;
	private String agentId ;
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
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	
}
