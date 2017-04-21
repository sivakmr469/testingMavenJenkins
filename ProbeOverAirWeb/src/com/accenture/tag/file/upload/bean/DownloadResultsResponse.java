package com.accenture.tag.file.upload.bean;

public class DownloadResultsResponse {
	private byte[] resultByte;
	private String resultCode;
	private String resultDescription;
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
	/**
	 * @return the resultDescription
	 */
	public String getResultDescription() {
		return resultDescription;
	}
	/**
	 * @param resultDescription the resultDescription to set
	 */
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
