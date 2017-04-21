package com.accenture.pota.bean;

import java.util.List;

import javax.ejb.Local;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentResponse;
import com.accenture.pota.entity.management.request.BLRegisterDeviceRequest;
import com.accenture.pota.entity.management.response.BLRegisterDeviceResponse;
import com.accenture.pota.entity.management.response.BLRetrieveDeviceResponse;
import com.accenture.pota.utils.TagException;

@Local
public interface RegisterDeviceLocal {
	public BLRegisterDeviceResponse registerDevice(BLRegisterDeviceRequest request) throws TagException;
	public BLRegisterDeviceAndAgentResponse registerDeviceAndAgent(BLRegisterDeviceAndAgentRequest request) throws TagException;
	public List<DalDeviceDetail> retrieveDeviceList() throws TagException;	
}

