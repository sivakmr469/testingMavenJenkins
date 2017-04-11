package com.accenture.pota.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "STATUS_DETAIL")
public class Status {
	
	private String statusName;
	private String statusId;
	
	public Status() {
		super();
	}
	public Status(String name, String id) {
		super();
		this.statusName = name;
		this.statusId = id;
	}
	@Column(name = "STATUS_NAME", unique = true, nullable = false)
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String name) {
		this.statusName = name;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STATUS_ID", unique = true, nullable = false)
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

}
