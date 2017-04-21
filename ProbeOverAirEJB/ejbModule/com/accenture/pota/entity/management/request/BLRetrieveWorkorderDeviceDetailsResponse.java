package com.accenture.pota.entity.management.request;

import javax.xml.bind.annotation.XmlElement;

public class BLRetrieveWorkorderDeviceDetailsResponse {
	
    protected String deviceName;
    protected String location;
    protected String status;
    protected String allocationId;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAllocationId() {
		return allocationId;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

    

}
