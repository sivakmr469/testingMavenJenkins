package com.accenture.pota.dal.exception;

import com.accenture.pota.utils.TagException;

public class TagDalException extends TagException {

	
	/**
	 * 
	 */
	// private static final long serialVersionUID  = 262233963806135287L;

	/**
	 * 
	 */
	

	public TagDalException( String code, String description, Throwable cause ) {
		super( code, description, cause );
	}

	public TagDalException( String code, String description ) {
		super( code, description );
	}


}
