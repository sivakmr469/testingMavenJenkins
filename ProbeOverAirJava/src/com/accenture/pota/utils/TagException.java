package com.accenture.pota.utils;

public class TagException extends Exception{
	/**
	 * 
	 */
	// private static final long serialVersionUID  = 8227871239672718330L;
	private String code;
	private String description;
	
	public TagException() {
		super();
	} 

	public TagException(String code, String description) {

		super("code [" + code + "] description [" + description + "]");
		this.code = code;
		this.description = description;

	}

	public TagException(String code, String description, Throwable cause) {

		super("code [" + code + "] description [" + description + "]", cause);
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
