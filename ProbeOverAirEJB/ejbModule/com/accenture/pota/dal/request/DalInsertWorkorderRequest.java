package com.accenture.pota.dal.request;

import java.io.Serializable;

import com.accenture.pota.utils.Device;

public class DalInsertWorkorderRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 3645863515757320461L;
	private String id;
	private String name;
	private String fileName;
	private String referenceName;
	private String description;
	private Device device ;
	
	
	public DalInsertWorkorderRequest() {
		super();
	}
	public DalInsertWorkorderRequest(String fileName, String referenceName) {
		super();
		this.fileName = fileName;
		this.referenceName = referenceName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}


}
