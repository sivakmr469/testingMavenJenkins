package com.accenture.pota.dal.facade.device;

import javax.ejb.Local;

import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.request.DalRegisterDeviceRequest;
import com.accenture.pota.dal.response.DalRegisterDeviceResponse;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;

@Local
public interface TagDalDeviceFacadeLocal {
	
	DalRegisterDeviceResponse registerDevice(DalRegisterDeviceRequest dalRequest)throws TagDalException;
	DalRegisterDeviceResponse registerDeviceAndAgent(DalRegisterDeviceRequest dalRequest)throws TagDalException;

	DalRetrieveDeviceResponse retrieveDevice(DalRegisterDeviceRequest dalRetrieveDeviceReq)throws TagDalException;
	DalRetrieveDeviceResponse retrieveDeviceList() throws TagDalException ;
	
	DalRetrieveDeviceResponse updateDeviceStatus() throws TagDalException ;
	

}
