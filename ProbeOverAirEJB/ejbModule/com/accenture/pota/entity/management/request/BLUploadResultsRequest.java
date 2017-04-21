package com.accenture.pota.entity.management.request;

import java.io.Serializable;

public class BLUploadResultsRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	private String allocationId;
	private byte[] resultByte;
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
	 * @return the resultByte
	 */
	public byte[] getResultByte() {
		return resultByte;
	}
	/**
	 * @param resultByte the resultByte to set
	 */
	public void setResultByte(byte[] resultByte) {
		this.resultByte = resultByte;
	}
	
}
