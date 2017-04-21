package com.accenture.pota.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE_DETAIL")
public class DeviceDetail {
	
	private String name;
	private String deviceId;
	private AgentDetail agent;
	private String location;
	private String deviceStatus;
	private String deviceType;
	private String user_group;
	private String lastPollTime;
	
	public DeviceDetail() {
		super();
	}
	public DeviceDetail(String name, String deviceId) {
		super();
		this.name = name;
		this.deviceId = deviceId;
	}
	@Column(name = "NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@Column(name = "DEVICE_ID", unique = true, nullable = false)
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT_ID", nullable = false)
	public AgentDetail getAgent() {
		return this.agent;
	}
	/**
	 * @param agent the agent to set
	 */
	public void setAgent(AgentDetail agent) {
		this.agent = agent;
	}
	@Column(name = "LOCATION")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "DEVICE_STATUS")
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	@Column(name = "DEVICE_TYPE")
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Column(name = "USER_GROUP")
	public String getUser_group() {
		return user_group;
	}
	public void setUser_group(String user_group) {
		this.user_group = user_group;
	}
	@Column(name = "Last_poll_time")
	public String getLastPollTime() {
		return lastPollTime;
	}
	public void setLastPollTime(String lastPollTime) {
		this.lastPollTime = lastPollTime;
	}
	
	

}
