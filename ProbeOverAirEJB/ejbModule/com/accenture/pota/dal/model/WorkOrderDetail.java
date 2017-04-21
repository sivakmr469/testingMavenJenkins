package com.accenture.pota.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "WORKORDER_DETAIL")
public class WorkOrderDetail {
	
	private String workorderId;
	private String name;
	private String description;
	private String fileName;
	private String referenceName;
	private String uploadedby;
	private String uploadtime;
	private String lastPollTime;
	private Set<WorkorderAllocationDetail> workorderAllocationDetail = new HashSet<WorkorderAllocationDetail>(0);
	
	public WorkOrderDetail() {
		super();
	}
	public WorkOrderDetail(String id) {
		super();
		this.workorderId = id;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WORKORDER_ID", unique = true, nullable = false)
	public String getWorkorderId() {
		return workorderId;
	}
	public void setWorkorderId(String id) {
		this.workorderId = id;
	}
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name = "REFERENCE_NAME")
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	/**
	 * @return the name
	 */
	@Column(name = "WORKORDER_NAME",unique = true, nullable = false)
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workorder")
	public Set<WorkorderAllocationDetail> getWorkorderAllocationDetail() {
		return this.workorderAllocationDetail;
	}

	public void setWorkorderAllocationDetail(Set<WorkorderAllocationDetail> workorderAllocationDetail) {
		this.workorderAllocationDetail = workorderAllocationDetail;
	}
	@Column(name = "UPLOADTED_BY")

	public String getUploadedby() {
		return uploadedby;
	}
	public void setUploadedby(String uploadedby) {
		this.uploadedby = uploadedby;
	}
	@Column(name = "UPLOAD_TIME")

	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
/*	@Column(name = "LAST_POLL_TIME")
	public String getLastPollTime() {
		return lastPollTime;
	}
	public void setLastPollTime(String lastPollTime) {
		this.lastPollTime = lastPollTime;
	}
*/	
	
}
