package com.accenture.pota.entity.management.response;

import java.io.Serializable;

import com.accenture.pota.utils.BLResponse;

public class BLRetrieveWorkorderResponse extends BLResponse implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	private byte[] fileByte;
	private String workorderName;
	private String workorderId;
	private String allocationId;
	/**
	 * @return the fileByte
	 */
	public byte[] getFileByte() {
		return fileByte;
	}
	/**
	 * @return the workorderName
	 */
	public String getWorkorderName() {
		return workorderName;
	}
	/**
	 * @param fileByte the fileByte to set
	 */
	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}
	/**
	 * @param workorderName the workorderName to set
	 */
	public void setWorkorderName(String workorderName) {
		this.workorderName = workorderName;
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
	
	
	

}
