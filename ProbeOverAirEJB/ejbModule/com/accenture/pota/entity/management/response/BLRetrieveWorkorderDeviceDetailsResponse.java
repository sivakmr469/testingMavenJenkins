package com.accenture.pota.entity.management.response;

import java.io.Serializable;
import java.util.List;

import com.accenture.pota.dal.bean.DeviceDetailsListBean;
import com.accenture.pota.utils.BLResponse;


public class BLRetrieveWorkorderDeviceDetailsResponse  extends BLResponse implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = 1L;
	protected List<DeviceDetailsListBean> deviceDetailsList;

	public List<DeviceDetailsListBean> getDeviceDetailsList() {
		return deviceDetailsList;
	}

	public void setDeviceDetailsList(List<DeviceDetailsListBean> deviceDetailsList) {
		this.deviceDetailsList = deviceDetailsList;
	}
	
	
}
