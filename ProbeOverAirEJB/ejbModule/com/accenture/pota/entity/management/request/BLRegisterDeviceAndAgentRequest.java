package com.accenture.pota.entity.management.request;

import java.io.Serializable;

public class BLRegisterDeviceAndAgentRequest implements Serializable{
	/**
	 * 
	 */
	// private static final long serialVersionUID  = 1L;
	private String deviceName;
	private String deviceType;
	private String deviceID;
	private String agentName;
	private String location;
	private String OS;
	private String RAM;
	private String MACID;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getAgentName() {
		return agentName;
	}
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
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	
	

}
