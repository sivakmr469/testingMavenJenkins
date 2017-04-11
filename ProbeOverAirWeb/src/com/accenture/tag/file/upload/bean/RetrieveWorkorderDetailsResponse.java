package com.accenture.tag.file.upload.bean;

import java.util.List;

public class RetrieveWorkorderDetailsResponse {

	  private List<WorkorderList> workorderList;
	  /**
	 * @return the workorderList
	 */
	public List<WorkorderList> getWorkorderList() {
		return workorderList;
	}
	/**
	 * @param workorderList the workorderList to set
	 */
	public void setWorkorderList(List<WorkorderList> workorderList) {
		this.workorderList = workorderList;
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
	private String resultCode;
	  private String resultDescription;
}
