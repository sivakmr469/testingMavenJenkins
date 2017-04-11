package com.accenture.pota.entity.management.response;

import java.io.Serializable;

import com.accenture.pota.utils.BLResponse;

public class BLDownloadResultsResponse extends BLResponse implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	private byte[] resultByte;
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
