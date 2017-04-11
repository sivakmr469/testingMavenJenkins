package com.accenture.pota.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityExistsException;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.facade.device.TagDalDeviceFacadeLocal;
import com.accenture.pota.dal.model.DeviceDetail;
import com.accenture.pota.dal.model.DeviceDetailHome;
import com.accenture.pota.dal.request.DalRegisterDeviceRequest;
import com.accenture.pota.dal.response.DalRegisterDeviceResponse;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;
import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.entity.management.exception.TagEntityManagementException;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentRequest;
import com.accenture.pota.entity.management.request.BLRegisterDeviceAndAgentResponse;
import com.accenture.pota.entity.management.request.BLRegisterDeviceRequest;
import com.accenture.pota.entity.management.response.BLRegisterDeviceResponse;
import com.accenture.pota.entity.management.utils.TagEntityManagementMessage;
import com.accenture.pota.utils.TagException;
import com.accenture.pota.utils.TagUtils;

@Stateless
@Local( RegisterDeviceLocal.class )
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RegisterDeviceBean1 implements RegisterDeviceLocal {
	
	
	@EJB
	private TagDalDeviceFacadeLocal dbAccessDevice;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public BLRegisterDeviceResponse registerDevice(
			BLRegisterDeviceRequest request) throws TagException {
		BLRegisterDeviceResponse  blResponse = new BLRegisterDeviceResponse();
		String name = request.getDeviceName();
		String agentName = request.getAgentName();
		try{
			if( TagUtils.isNull( name ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "device name" );
			if( TagUtils.isNull( agentName ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "agent name" );
			
			DalRegisterDeviceRequest  dalRequest = new DalRegisterDeviceRequest();
			dalRequest.setDeviceName(name);
			dalRequest.setAgentName(agentName);
			DalRegisterDeviceResponse dalResponse = dbAccessDevice.registerDevice(dalRequest);
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
		}
		catch (Exception e) {
	
			if (e instanceof TagDalException){
				throw new TagEntityManagementException(((TagDalException) e).getCode(), ((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException){
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(), ((TagEntityManagementException) e).getDescription());
			}
	
		}

		return blResponse;
	}
	
	
	public List<DalDeviceDetail> retrieveDeviceList() throws TagException {
		System.out.println();
		List<DalDeviceDetail> devList=new ArrayList<DalDeviceDetail>();
		BLRegisterDeviceResponse  blResponse = new BLRegisterDeviceResponse();
		DalRetrieveDeviceResponse dalResponse = new DalRetrieveDeviceResponse();
		try{
			dalResponse = dbAccessDevice.retrieveDeviceList();
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
			List<DalDeviceDetail> deviceList=dalResponse.getDeviceList();
			devList = dalResponse.getDeviceList();
			/*
			if(deviceList != null){
				dalResponse.getDeviceList().addAll(devList);
			}*/
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			e.printStackTrace();
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
		
		}
		catch(Exception ex){
			ex.printStackTrace();;
			if (ex instanceof TagDalException){
				throw new TagDalException(((TagDalException) ex).getCode(), ((TagDalException) ex).getDescription());
			}else{
				dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
				dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
			}
	
		//return new BLRegisterDeviceResponse();
		}	
		return devList;
	}


	@Override
	public BLRegisterDeviceAndAgentResponse registerDeviceAndAgent(BLRegisterDeviceAndAgentRequest request)
			throws TagException {
		BLRegisterDeviceAndAgentResponse blResponse=new BLRegisterDeviceAndAgentResponse();
		String name = request.getDeviceName();
		String agentName = request.getAgentName();
		String location = request.getLocation();
		String OS = request.getOS();
		String RAM = request.getRAM();
		String MACID = request.getMACID();
		try{
			if( TagUtils.isNull( name ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "device name" );
			if( TagUtils.isNull( agentName ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "agent name" );

			if( TagUtils.isNull( location ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "location" );
			if( TagUtils.isNull( OS) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "OS" );
			if( TagUtils.isNull( RAM ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "RAM" );
			if( TagUtils.isNull( MACID ) ) 
				throw new TagEntityManagementException( TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST,
						TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC + "MAC ID" );
		
			DalRegisterDeviceRequest  dalRequest = new DalRegisterDeviceRequest();
			dalRequest.setDeviceId(MACID);
			dalRequest.setDeviceName(name);
			dalRequest.setAgentName(agentName);
			dalRequest.setLocation(location);
			dalRequest.setOS(OS);
			dalRequest.setRAM(RAM);
			dalRequest.setMACID(MACID);
			dalRequest.setDeviceType(request.getDeviceType());
			
			DalRegisterDeviceResponse dalResponse = dbAccessDevice.registerDeviceAndAgent(dalRequest);
			blResponse.setAgentID(dalResponse.getAgentId());
			blResponse.setResultCode(dalResponse.getCode());
			blResponse.setResultDescription(dalResponse.getDescription());
		}
		catch (Exception e) {
	
			if (e instanceof TagDalException){
				throw new TagEntityManagementException(((TagDalException) e).getCode(), ((TagDalException) e).getDescription());
			}
			if (e instanceof TagEntityManagementException){
				throw new TagEntityManagementException(((TagEntityManagementException) e).getCode(), ((TagEntityManagementException) e).getDescription());
			}
	
		}

		return blResponse;
	}
}
