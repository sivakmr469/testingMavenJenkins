package com.accenture.tag.file.upload.bean;

import java.util.List;

public class CreateWorkorderRequest {
	
	private String fileName;
	private byte[] fileByte;
	private String description;
	private String name;
	private List<Device>deviceList;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileByte
	 */
	public byte[] getFileByte() {
		return fileByte;
	}
	/**
	 * @param fileByte the fileByte to set
	 */
	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
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
	 * @return the deviceList
	 */
	public List<Device> getDeviceList() {
		return deviceList;
	}
	/**
	 * @param deviceList the deviceList to set
	 */
	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}
	
	
}
