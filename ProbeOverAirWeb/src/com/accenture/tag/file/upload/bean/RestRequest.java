package com.accenture.tag.file.upload.bean;

public class RestRequest {
	
	
	private String restUrl;
	private Object requestObject;
	
	public String getRestUrl() {
		return restUrl;
	}
	public void setRestUrl(final String restUrl) {
		this.restUrl = restUrl;
	}
	/**
	 * @return the requestObject
	 */
	public Object getRequestObject() {
		return requestObject;
	}
	/**
	 * @param requestObject the requestObject to set
	 */
	public void setRequestObject(Object requestObject) {
		this.requestObject = requestObject;
	}


}
