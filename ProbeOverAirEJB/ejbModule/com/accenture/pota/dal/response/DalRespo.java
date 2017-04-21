package com.accenture.pota.dal.response;

import com.accenture.pota.dal.utils.Message;


public class DalRespo implements java.io.Serializable {
	
	// private static final long serialVersionUID  = -4025541942185924579L;

	private String code;
	private String description;
	
	
	public DalRespo() {
		super();
		this.code = Message.DAL_OK_CODE_RESPONSE;
		this.description = Message.DAL_OK_DESC_RESPONSE;
	}

	public DalRespo( String code, String description ) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
