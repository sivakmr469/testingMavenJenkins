package com.accenture.pota.entity.management.exception;

import com.accenture.pota.utils.TagException;

public class TagEntityManagementException extends TagException{

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -2893817287296776237L;

	public TagEntityManagementException(String code, String description, Throwable cause) {
		super(code, description, cause);
	}

	public TagEntityManagementException(String code, String description) {
		super(code, description);
	}

}
