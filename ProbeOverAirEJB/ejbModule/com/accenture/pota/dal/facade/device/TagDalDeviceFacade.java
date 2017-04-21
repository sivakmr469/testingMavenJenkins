package com.accenture.pota.dal.facade.device;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.model.AgentDetail;
import com.accenture.pota.dal.model.AgentDetailHome;
import com.accenture.pota.dal.model.DeviceDetail;
import com.accenture.pota.dal.model.DeviceDetailHome;
import com.accenture.pota.dal.request.DalRegisterDeviceRequest;
import com.accenture.pota.dal.response.DalRegisterDeviceResponse;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;
import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.utils.Device;

@Stateless(name = "DeviceDalController")
@Local(TagDalDeviceFacadeLocal.class)
public class TagDalDeviceFacade implements TagDalDeviceFacadeLocal{
	
	@PersistenceContext( unitName = "tagPU" )
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalRegisterDeviceResponse registerDevice(
			DalRegisterDeviceRequest dalRequest) throws TagDalException {
		DalRegisterDeviceResponse dalResponse = new DalRegisterDeviceResponse();
		try{
			if( dalRequest == null || dalRequest.getDeviceName() == null || dalRequest.getAgentName() == null){
				throw new TagDalException( Message.DAL_MANDATORY_PARAMS_ERROR_CODE,
						   				   Message.DAL_MANDATORY_PARAMS_ERROR_DESC );
			}
			DeviceDetailHome deviceHome = new DeviceDetailHome( entityManager );
			AgentDetailHome agentHome = new AgentDetailHome(entityManager);
			@SuppressWarnings("unchecked")
			List<AgentDetail> agentList = agentHome.findAgentByName(dalRequest.getAgentName());
			AgentDetail agentDetail= null;
			if(agentList != null && agentList.size()!= 0){
				agentDetail = agentList.get(0);
			}else{
				throw new TagDalException( Message.DAL_AGENT_NOT_FOUND_CODE,
		   				   Message.DAL_AGENT_NOT_FOUND_DESC );
			}
			
			DeviceDetail deviceToRegi = new DeviceDetail();
			deviceToRegi.setName(dalRequest.getDeviceName());
			
			deviceToRegi.setAgent(agentDetail);
			deviceHome.persist( deviceToRegi ); 
			
			if(deviceToRegi.getDeviceId() == null){
				throw new TagDalException(Message.DAL_REGISTER_DEVICE_GENERIC_ERROR_CODE, 
										  Message.DAL_REGISTER_DEVICE_GENERIC_ERROR_DESC);
			}
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}catch(PersistenceException pe){
			String causeString = pe.getCause().toString();
			if (causeString != null) 
			{
				if (causeString.contains("org.hibernate.exception.ConstraintViolationException")) 
				{
					throw new TagDalException( Message.DAL_NOT_UNIQUE_VALUE_ERROR_CODE,
							   Message.DAL_NOT_UNIQUE_VALUE_ERROR_DESC + "device name" );
				} 
			}

		}
		catch(Exception ex){
			if (ex instanceof TagDalException){
				throw new TagDalException(((TagDalException) ex).getCode(), ((TagDalException) ex).getDescription());
			}else{
				dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
				dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
			}
			
		}
		
		return dalResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalRetrieveDeviceResponse retrieveDevice(
			DalRegisterDeviceRequest dalRetrieveDeviceReq)
			throws TagDalException {
		DalRetrieveDeviceResponse dalResponse = new DalRetrieveDeviceResponse();
		try{
			if(dalRetrieveDeviceReq == null || dalRetrieveDeviceReq.getDeviceName() == null){
				throw new TagDalException( Message.DAL_MANDATORY_PARAMS_ERROR_CODE,
						   				   Message.DAL_MANDATORY_PARAMS_ERROR_DESC );
			}
			DeviceDetailHome deviceHome = new DeviceDetailHome( entityManager );
			Device device = new Device();
			device.setDeviceName(dalRetrieveDeviceReq.getDeviceName());
			@SuppressWarnings("unchecked")
			List<DeviceDetail> deviceList = deviceHome.findByFilter(device);
			List<DalDeviceDetail> devList = new ArrayList<DalDeviceDetail>();
			for (DeviceDetail deviceDetail : deviceList) {
				DalDeviceDetail dalDeviceBean = new DalDeviceDetail();
				dalDeviceBean.setDeviceId(deviceDetail.getDeviceId());
				dalDeviceBean.setDeviceName(deviceDetail.getName());
				devList.add(dalDeviceBean);
			}
			
			
			if(deviceList != null){
				dalResponse.getDeviceList().addAll(devList);
			}
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		catch(Exception ex){
			if (ex instanceof TagDalException){
				throw new TagDalException(((TagDalException) ex).getCode(), ((TagDalException) ex).getDescription());
			}else{
				dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
				dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
			}
			
		}
		
		return dalResponse;
	}
//------------------------------------------------------------------------------------------------------------
	public DalRetrieveDeviceResponse retrieveDeviceList()
			throws TagDalException {
		DalRetrieveDeviceResponse dalResponse = new DalRetrieveDeviceResponse();
		try{
			DeviceDetailHome deviceHome = new DeviceDetailHome( entityManager );
			@SuppressWarnings("unchecked")
			List<DeviceDetail> deviceList = deviceHome.findAll();
			List<DalDeviceDetail> devList = new ArrayList<DalDeviceDetail>();
			for (DeviceDetail deviceDetail : deviceList) {
				DalDeviceDetail dalDeviceBean = new DalDeviceDetail();
				dalDeviceBean.setDeviceId(deviceDetail.getDeviceId());
				dalDeviceBean.setDeviceName(deviceDetail.getName());
				dalDeviceBean.setLocation(deviceDetail.getLocation());
				dalDeviceBean.setDeviceStatus(deviceDetail.getDeviceStatus());
				devList.add(dalDeviceBean);
			}
			
			
			if(deviceList != null){
				dalResponse.getDeviceList().addAll(devList);
			}
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			e.printStackTrace();
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		catch(Exception ex){
			ex.printStackTrace();;
			if (ex instanceof TagDalException){
				throw new TagDalException(((TagDalException) ex).getCode(), ((TagDalException) ex).getDescription());
			}else{
				dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
				dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
			}
			
		}
		
		return dalResponse;
	}

	@Override
	public DalRegisterDeviceResponse registerDeviceAndAgent(DalRegisterDeviceRequest dalRequest)
			throws TagDalException {
		DalRegisterDeviceResponse dalResponse = new DalRegisterDeviceResponse();
		try{
			if( dalRequest == null || dalRequest.getDeviceName() == null || dalRequest.getAgentName() == null){
				throw new TagDalException( Message.DAL_MANDATORY_PARAMS_ERROR_CODE,
						   				   Message.DAL_MANDATORY_PARAMS_ERROR_DESC );
			}
			
			
			
			AgentDetailHome agentHome = new AgentDetailHome( entityManager );
			
			AgentDetail agentToInsert = new AgentDetail();
			agentToInsert.setName(dalRequest.getAgentName());
			agentToInsert.setOS(dalRequest.getOS());
			agentToInsert.setRAM(dalRequest.getRAM());
			agentToInsert.setMACID(dalRequest.getMACID());
			
			agentHome.persist( agentToInsert ); 
			
			if(agentToInsert.getAgentId() == null){
				throw new TagDalException(Message.DAL_INSERT_AGENT_GENERIC_ERROR_CODE, 
										  Message.DAL_INSERT_AGENT_GENERIC_ERROR_DESC);
			}
			dalResponse.setAgentId(agentToInsert.getAgentId());

			
			
			DeviceDetailHome deviceHome = new DeviceDetailHome( entityManager );
			
			@SuppressWarnings("unchecked")
			List<AgentDetail> agentList = agentHome.findAgentByName(dalRequest.getAgentName());
			AgentDetail agentDetail= null;
			if(agentList != null && agentList.size()!= 0){
				agentDetail = agentList.get(0);
				System.out.println("agen idis : "+agentDetail.getAgentId());
			}else{
				throw new TagDalException( Message.DAL_AGENT_NOT_FOUND_CODE,
		   				   Message.DAL_AGENT_NOT_FOUND_DESC );
			}
			
			DeviceDetail deviceToRegi = new DeviceDetail();
			deviceToRegi.setName(dalRequest.getDeviceName());
			deviceToRegi.setLocation(dalRequest.getLocation());
			deviceToRegi.setUser_group("GEN");
			deviceToRegi.setDeviceType(dalRequest.getDeviceType());
			deviceToRegi.setDeviceStatus("Avaialble");
			deviceToRegi.setAgent(agentDetail);
			deviceToRegi.setDeviceId(dalRequest.getMACID());
			deviceToRegi.setLastPollTime(null);
			deviceHome.persist( deviceToRegi ); 
			
		/*	if(deviceToRegi.getDeviceId() == null){
				throw new TagDalException(Message.DAL_REGISTER_DEVICE_GENERIC_ERROR_CODE, 
										  Message.DAL_REGISTER_DEVICE_GENERIC_ERROR_DESC);
			}
		*/	dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			e.printStackTrace();
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}catch(PersistenceException pe){
			pe.printStackTrace();
			String causeString = pe.getCause().toString();
			if (causeString != null) 
			{
				if (causeString.contains("org.hibernate.exception.ConstraintViolationException")) 
				{
					throw new TagDalException( Message.DAL_NOT_UNIQUE_VALUE_ERROR_CODE,
							   Message.DAL_NOT_UNIQUE_VALUE_ERROR_DESC + "device name" );
				} 
			}

		}
		catch(Exception ex){
			if (ex instanceof TagDalException){
				throw new TagDalException(((TagDalException) ex).getCode(), ((TagDalException) ex).getDescription());
			}else{
				dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
				dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
			}
			
		}
		
		return dalResponse;
	}

	@Override
	public DalRetrieveDeviceResponse updateDeviceStatus() throws TagDalException {
	
		DeviceDetailHome deviceHome = new DeviceDetailHome( entityManager );
		
		return null;
	}

}
