package com.accenture.pota.dal.response;

import java.util.ArrayList;
import java.util.List;

import com.accenture.pota.dal.bean.DalDeviceDetail;

public class DalRetrieveDeviceResponse extends DalRespo {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -6862696502117306180L;
	
	private List<DalDeviceDetail> deviceList;

	/**
	 * @return the deviceList
	 */
	public List<DalDeviceDetail> getDeviceList() {
		if(deviceList == null){
			deviceList = new ArrayList<DalDeviceDetail>();
		}
		return deviceList;
	}
	

}
