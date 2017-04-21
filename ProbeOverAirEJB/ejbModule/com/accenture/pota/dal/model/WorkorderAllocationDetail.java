package com.accenture.pota.dal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WORKORDER_ALLOCATION")
public class WorkorderAllocationDetail {
	
	private String allocationId;
	private WorkOrderDetail workorder;
	private DeviceDetail deviceDetail;
	private Date allocationDate;
	private Date executionCompletionDate;
	private byte[] result;
	private Status status;
	
	public WorkorderAllocationDetail() {
		super();
	}
	
	public WorkorderAllocationDetail(String allocationId, WorkOrderDetail workorder,
			DeviceDetail deviceDetail, Date allocationDate, Date executionCompletionDate,
			byte[] result) {
		super();
		this.allocationId = allocationId;
		this.workorder = workorder;
		this.deviceDetail = deviceDetail;
		this.allocationDate = allocationDate;
		this.executionCompletionDate = executionCompletionDate;
		this.result = result;
	}


	/**
	 * @return the allocationId
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ALLOCATION_ID", unique = true, nullable = false)
	public String getAllocationId() {
		return allocationId;
	}

	/**
	 * @param allocationId the allocationId to set
	 */
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	/**
	 * @return the workorder
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKORDER_ID", nullable = false)
	public WorkOrderDetail getWorkorder() {
		return workorder;
	}

	/**
	 * @param workorder the WorkOrderDetail to set
	 */
	public void setWorkorder(WorkOrderDetail workorder) {
		this.workorder = workorder;
	}

	/**
	 * @return the deviceDetail
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_ID", nullable = false)
	public DeviceDetail getDeviceDetail() {
		return deviceDetail;
	}

	/**
	 * @param DeviceDetail the DeviceDetail to set
	 */
	public void setDeviceDetail(DeviceDetail deviceDetail) {
		this.deviceDetail = deviceDetail;
	}

	/**
	 * @return the allocationDate
	 */
	@Column(name = "ALLOCATION_DATE")
	public Date getAllocationDate() {
		return allocationDate;
	}

	/**
	 * @param allocationDate the allocationDate to set
	 */
	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

	/**
	 * @return the executionCompletionDate
	 */
	@Column(name = "EXECUTION_END_DATE")
	public Date getExecutionCompletionDate() {
		return executionCompletionDate;
	}

	/**
	 * @param executionCompletionDate the executionCompletionDate to set
	 */
	public void setExecutionCompletionDate(Date executionCompletionDate) {
		this.executionCompletionDate = executionCompletionDate;
	}

	/**
	 * @return the result
	 */
	@Column(name = "RESULTS")
	public byte[] getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(byte[] result) {
		this.result = result;
	}

	/**
	 * @return the status
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS_ID", nullable = false)
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	

}
