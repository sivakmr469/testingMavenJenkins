package com.accenture.tag.file.upload.bean;

import java.io.Serializable;

public class DownloadResultsRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9140842747550945076L;
	private String allocationId;
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
