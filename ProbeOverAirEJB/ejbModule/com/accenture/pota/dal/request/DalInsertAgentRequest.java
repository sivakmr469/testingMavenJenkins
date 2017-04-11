package com.accenture.pota.dal.request;

import java.io.Serializable;

public class DalInsertAgentRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 3645863515757320461L;
	private String name;
	private String id;
	
	
	public DalInsertAgentRequest() {
		super();
	}
	public DalInsertAgentRequest(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
