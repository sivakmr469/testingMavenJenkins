package com.accenture.pota.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "AGENT")
public class AgentDetail {
	
	private String name;
	private String OS;
	private String agentId;
	private String MACID;
	private String RAM;
	
	
	public AgentDetail() {
		super();
	}
	public AgentDetail(String name, String id) {
		super();
		this.name = name;
		this.agentId = id;
	}
	@Column(name = "NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AGENT_ID", unique = true, nullable = false)
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	@Column(name = "OS")
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	@Column(name = "MAC_ID")
	public String getMACID() {
		return MACID;
	}
	public void setMACID(String mACID) {
		MACID = mACID;
	}
	@Column(name = "RAM")
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}

	
	
}
