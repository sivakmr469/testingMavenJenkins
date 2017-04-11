package com.accenture.pota.dal.response;

import java.util.List;

import com.accenture.pota.dal.bean.DeviceDetailsListBean;

public class DalRetrieveWordkorderDeviceDetails extends DalRespo {
	
	protected List<DeviceDetailsListBean> deviceDetailsList;

	public List<DeviceDetailsListBean> getDeviceDetailsList() {
		return deviceDetailsList;
	}

	public void setDeviceDetailsList(List<DeviceDetailsListBean> deviceDetailsList) {
		this.deviceDetailsList = deviceDetailsList;
	}
	
}
