package com.accenture.pota.entity.management.response;

import java.io.Serializable;

import com.accenture.pota.utils.BLResponse;

public class BLInsertWorkorderResponse extends BLResponse implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	
	
	private String id;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

}
