package com.accenture.pota.entity.management.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.accenture.pota.utils.Device;

public class BLInsertWorkorderRequest implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -9140842747550945076L;
	
	private String id;
	
	private String description;
	
	private byte[] fileBytes;
	
	private String fileName;
	
	private String name;
	
	private List<Device> deviceList;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	 * @return the deviceList
	 */
	public List<Device> getDeviceList() {
		if(deviceList == null){
			deviceList = new ArrayList<Device>();
		}
		return deviceList;
	}


}
