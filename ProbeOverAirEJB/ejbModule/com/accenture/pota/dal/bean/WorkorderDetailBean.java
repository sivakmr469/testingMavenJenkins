package com.accenture.pota.dal.bean;
public class WorkorderDetailBean {

	private String workorderName;
	private String referenceName;
	private String workorderId;
	private String fileName;
	private String description;
	private String allocationId;
	private String statusId;
	private String deviceName;
	private String uploadedBy;
	private String uploadTime;
	private String lastPollTime; 
	
	/**
	 * @return the workorderName
	 */
	public String getWorkorderName() {
		return workorderName;
	}
	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}
	/**
	 * @param workorderName the workorderName to set
	 */
	public void setWorkorderName(String workorderName) {
		this.workorderName = workorderName;
	}
	/**
	 * @param referenceName the referenceName to set
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	/**
	 * @return the workorderId
	 */
	public String getWorkorderId() {
		return workorderId;
	}
	/**
	 * @param workorderId the workorderId to set
	 */
	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the allocationId
	 */
	public String getAllocationId() {
		return allocationId;
	}
	/**
	 * @param allocationId the allocationId to set
	 */
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
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
	 * @return the statusstatusIdName
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getLastPollTime() {
		return lastPollTime;
	}
	public void setLastPollTime(String lastPollTime) {
		this.lastPollTime = lastPollTime;
	}
	
	
}
