package com.accenture.tag.file.uploader.utility;

import java.util.List;
import java.util.Map;

public class Person {
	
	String script_file;
	String priority;
	List<String> workorderList;
	List<ProbeDetailsBean> probeList;
	Map<String,String> scriptsPriorityMap;
	private List<String> lstFileName;	
	
	
	public List<String> getLstFileName() {
		return lstFileName;
	}
	public void setLstFileName(List<String> lstFileName) {
		this.lstFileName = lstFileName;
	}
	public List<String> getWorkorderList() {
		return workorderList;
	}
	public void setWorkorderList(List<String> workorderList) {
		this.workorderList = workorderList;
	}
	public String getScript_file() {
		return script_file;
	}
	public void setScript_file(String script_file) {
		this.script_file = script_file;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public List<ProbeDetailsBean> getProbeList() {
		return probeList;
	}
	public void setProbeList(List<ProbeDetailsBean> probeList) {
		this.probeList = probeList;
	}
	public Map<String, String> getScriptsPriorityMap() {
		return scriptsPriorityMap;
	}
	public void setScriptsPriorityMap(Map<String, String> scriptsPriorityMap) {
		this.scriptsPriorityMap = scriptsPriorityMap;
	}
	
	
 
}
