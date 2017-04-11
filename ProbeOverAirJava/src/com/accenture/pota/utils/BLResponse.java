package com.accenture.pota.utils;

import java.sql.Timestamp;

public class BLResponse implements java.io.Serializable {
	
	// private static final long serialVersionUID  = 9198626490865397592L;
	
	private String resultCode;
	private String resultDescription;  
	
	
	protected Timestamp freshness = new Timestamp(System.currentTimeMillis());
	
	public BLResponse() {
		super();
		this.resultCode = Constants.OK_CODE_RESPONSE;
		this.resultDescription = Constants.OK_DESC_RESPONSE;
	}

	public BLResponse( String cod, String description ) {
		super();
		this.resultCode = cod;
		this.resultDescription = description;
	}
	
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode( String code ) {
		this.resultCode = code;
	}
	
	public String getResultDescription() {
		return resultDescription;
	}
	
	public void setResultDescription( String description ) {
		this.resultDescription = description;
	}

	public Timestamp getFreshness() {
		return freshness;
	}

	public void setFreshness(Timestamp freshness) {
		this.freshness = freshness;
	}

}
