package com.accenture.tag.file.uploader.utility;

import java.util.List;
import java.util.Map;

public class Poto {

	String probeName;
	String filepath;
	List<String> lstDevices;
	Map<String,String> scriptsMap;
	
	
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "Poto [probeName=" + probeName + ", lstDevices=" + lstDevices + ", scriptsMap=" + scriptsMap + "]";
	}
	public String getProbeName() {
		return probeName;
	}
	public void setProbeName(String probeName) {
		this.probeName = probeName;
	}
	public List<String> getLstDevices() {
		return lstDevices;
	}
	public void setLstDevices(List<String> lstDevices) {
		this.lstDevices = lstDevices;
	}
	public Map<String, String> getScriptsMap() {
		return scriptsMap;
	}
	public void setScriptsMap(Map<String, String> scriptsMap) {
		this.scriptsMap = scriptsMap;
	}
	
	
}
