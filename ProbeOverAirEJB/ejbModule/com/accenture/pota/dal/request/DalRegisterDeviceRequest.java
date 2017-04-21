package com.accenture.pota.dal.request;

import java.io.Serializable;

public class DalRegisterDeviceRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 3645863515757320461L;

	private String deviceName;
	private String deviceId;
	private String agentName;
	private String deviceType;

	private String location;
	private String OS;
	private String RAM;
	private String MACID;
	

	public DalRegisterDeviceRequest() {
		super();
	}
	public DalRegisterDeviceRequest(String deviceName, String deviceId,
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}
	public String getMACID() {
		return MACID;
	}
	public void setMACID(String mACID) {
		MACID = mACID;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	
}
