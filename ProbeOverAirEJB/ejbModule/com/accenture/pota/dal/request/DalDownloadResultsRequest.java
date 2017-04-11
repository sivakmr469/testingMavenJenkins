package com.accenture.pota.dal.request;

import java.io.Serializable;

public class DalDownloadResultsRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 3645863515757320461L;
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
