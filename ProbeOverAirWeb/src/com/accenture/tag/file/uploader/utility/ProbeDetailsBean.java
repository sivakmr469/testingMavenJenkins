package com.accenture.tag.file.uploader.utility;

import java.util.List;

public class ProbeDetailsBean {

	private List<String> lstFileName;
	private List<String> lstdevices;
	private String workOrderName;
	private String workOrderID;
	private String statusID;
	private String status;
	private String DeviceName;
	private String allocationID;
	
	
	
	public String getWorkOrderID() {
		return workOrderID;
	}
	public void setWorkOrderID(String workOrderID) {
		this.workOrderID = workOrderID;
	}
	public String getAllocationID() {
		return allocationID;
	}
	public void setAllocationID(String allocationID) {
		this.allocationID = allocationID;
	}
	public List<String> getLstdevices() {
		return lstdevices;
	}
	public void setLstdevices(List<String> lstdevices) {
		this.lstdevices = lstdevices;
	}
	public List<String> getLstFileName() {
		return lstFileName;
	}
	public void setLstFileName(List<String> lstFileName) {
		this.lstFileName = lstFileName;
	}
	public String getWorkOrderName() {
		return workOrderName;
	}
	public void setWorkOrderName(String workOrderName) {
		this.workOrderName = workOrderName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	public String getDeviceName() {
		return DeviceName;
	}
	public void setDeviceName(String deviceName) {
		DeviceName = deviceName;
	}
	
	
@Override
public String toString() {
	// TODO Auto-generated method stub
	StringBuffer buff=new StringBuffer();
	buff.append(workOrderName+"  ");
	return super.toString();
}	
	
}
