package com.accenture.pota.dal.facade.workorder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Session;

import com.accenture.pota.dal.bean.DeviceDetailsListBean;
import com.accenture.pota.dal.bean.WorkorderDetailBean;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.model.DeviceDetail;
import com.accenture.pota.dal.model.Status;
import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.dal.model.WorkorderAllocationDetail;
import com.accenture.pota.dal.model.WorkorderAllocationHome;
import com.accenture.pota.dal.model.WorkorderDetailHome;
import com.accenture.pota.dal.request.DalDownloadResultsRequest;
import com.accenture.pota.dal.request.DalInsertWorkorderRequest;
import com.accenture.pota.dal.request.DalRetrieveWorkorderRequest;
import com.accenture.pota.dal.request.DalUpdateResultsRequest;
import com.accenture.pota.dal.response.DalDownloadResultsResponse;
import com.accenture.pota.dal.response.DalInsertWorkorderResponse;
import com.accenture.pota.dal.response.DalRetrieveWordkorderDeviceDetails;
import com.accenture.pota.dal.response.DalRetrieveWorkorderDetailResponse;
import com.accenture.pota.dal.response.DalRetrieveWorkorderResponse;
import com.accenture.pota.dal.response.DalUpdateResultsResponse;
import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.utils.Device;
import com.accenture.pota.utils.TagException;
import com.accenture.pota.utils.TagUtils;
import com.accenture.pota.utils.WorkorderList;

@Stateless(name = "WorkorderDalController")
@Local(TagDalWorkorderFacadeLocal.class)
public class TagDalWorkorderFacade implements TagDalWorkorderFacadeLocal{
	@PersistenceContext( unitName = "tagPU" )
	private EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalInsertWorkorderResponse insertWorkorder(
			DalInsertWorkorderRequest dalRequest) throws TagDalException {
		DalInsertWorkorderResponse dalResponse = new DalInsertWorkorderResponse();
		try{
			if( dalRequest == null || dalRequest.getFileName() == null||dalRequest.getName()== null){
				throw new TagDalException( Message.DAL_MANDATORY_PARAMS_ERROR_CODE,
						   				   Message.DAL_MANDATORY_PARAMS_ERROR_DESC );
			}
			WorkorderDetailHome workorderHome = new WorkorderDetailHome( entityManager );
			 String currTimeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
			WorkOrderDetail woToInsert = new WorkOrderDetail();
			woToInsert.setFileName(dalRequest.getFileName());
			woToInsert.setDescription(dalRequest.getDescription());
			woToInsert.setReferenceName(dalRequest.getReferenceName());
			woToInsert.setName(dalRequest.getName());
			woToInsert.setUploadtime(currTimeStamp);
			woToInsert.setUploadedby("Admin");
			workorderHome.persist( woToInsert ); 
			
			if(woToInsert.getWorkorderId() == null){
				throw new TagDalException(Message.DAL_INSERT_WORKORDER_GENERIC_ERROR_CODE, 
										  Message.DAL_INSERT_WORKORDER_GENERIC_ERROR_DESC);
			}
			dalResponse.setId(woToInsert.getWorkorderId());
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(EntityExistsException e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		catch(PersistenceException pe){
			String causeString = pe.getCause().toString();
			if (causeString != null) 
			{
				if (causeString.contains("org.hibernate.exception.ConstraintViolationException")) 
				{
					throw new TagDalException( Message.DAL_NOT_UNIQUE_VALUE_ERROR_CODE,
							   Message.DAL_NOT_UNIQUE_VALUE_ERROR_DESC + "probe name" );
				} 
			}

		}
		catch(Exception ex){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		return dalResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalInsertWorkorderResponse insertWorkorderAllocation(
			DalInsertWorkorderRequest dalRequest) throws TagDalException {
		DalInsertWorkorderResponse dalResponse = new DalInsertWorkorderResponse();
		try{
			if( dalRequest == null || TagUtils.isEmpty(dalRequest.getId())){
				throw new TagDalException( Message.DAL_MANDATORY_PARAMS_ERROR_CODE,
						   				   Message.DAL_MANDATORY_PARAMS_ERROR_DESC );
			}
			WorkorderAllocationHome workorderAllocationHome = new WorkorderAllocationHome( entityManager );
			
			Device device = dalRequest.getDevice();
			DeviceDetail deviceDetail = new DeviceDetail();
			deviceDetail.setDeviceId(device.getDeviceId());
				WorkorderAllocationDetail woAllocationToInsert = new WorkorderAllocationDetail();
				WorkOrderDetail woDetail = new WorkOrderDetail();
				woDetail.setWorkorderId(dalRequest.getId());
				woAllocationToInsert.setWorkorder(woDetail);
				woAllocationToInsert.setAllocationDate(new Date());
				woAllocationToInsert.setDeviceDetail(deviceDetail);
				Status status = new Status();
				status.setStatusId("1");
				woAllocationToInsert.setStatus(status);
				workorderAllocationHome.persist( woAllocationToInsert ); 
				if(woAllocationToInsert.getAllocationId() == null){
					throw new TagDalException(Message.DAL_INSERT_WORKORDER_ALLOCATION_GENERIC_ERROR_CODE, 
											  Message.DAL_INSERT_WORKORDER_ALLOCATION_GENERIC_ERROR_DESC);
				}
				dalResponse.setId(woAllocationToInsert.getWorkorder().getWorkorderId());
				dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
				dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
			
		}catch(EntityExistsException e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		catch(Exception ex){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		return dalResponse;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalRetrieveWorkorderResponse retrieveWorkorder(DalRetrieveWorkorderRequest dalRequest) {
		Query sqlWORetrievalQuery = null;
		List woList     = null;
		List<WorkorderDetailBean> woBeanlist = new ArrayList<WorkorderDetailBean>();
		DalRetrieveWorkorderResponse dalResponse = new DalRetrieveWorkorderResponse();
		
		/*String sqlWORetrieval= "select WO.referenceName,WO.name,WO.workorderId,WO.uploadedby,WO.uploadtime,WA.allocationId from c,WorkorderAllocationDetail WA, "
				+ "DeviceDetail DD,AgentDetail A where " +
		"A.name='"+dalRequest.getAgentName()+"' and DD.name ='"+ dalRequest.getDeviceName()+"' and A.agentId = DD.agent.agentId and DD.deviceId=WA.deviceDetail.deviceId"
				+ " and WA.workorder.workorderId=WO.workorderId and WA.status.statusId=1 ORDER BY WA.allocationDate";
*/
		String sqlWORetrieval= "select WO.referenceName,WO.name,WO.workorderId,WO.uploadedby,WO.uploadtime,WA.allocationId from WorkOrderDetail WO,WorkorderAllocationDetail WA, "
				+ "DeviceDetail DD,AgentDetail A where A.name='"+dalRequest.getAgentName()+"' and DD.name ='"+ dalRequest.getDeviceName()+
				"' and A.agentId = DD.agent.agentId and DD.deviceId=WA.deviceDetail.deviceId and WA.workorder.workorderId=WO.workorderId "
				+ "and WA.status.statusId=1 ORDER BY WA.allocationDate";
		try{
			sqlWORetrievalQuery = entityManager.createQuery(sqlWORetrieval);
			woList=sqlWORetrievalQuery.getResultList();	
			System.out.println("woList check size :: "+(woList.size()));
			int idx =0;
			if ((woList!=null)&&(woList.size()>0)){
				while (idx<woList.size()){
					WorkorderDetailBean  bean = new WorkorderDetailBean();
					String refName =(String)((Object[])woList.get(idx))[0];
					bean.setReferenceName(refName);
					String woName = (String)((Object[])woList.get(idx))[1];
					bean.setWorkorderName(woName);
					String woId = (String)((Object[])woList.get(idx))[2];
					bean.setWorkorderId(woId);
					String uploadedby = (String)((Object[])woList.get(idx))[3];
					bean.setUploadedBy(uploadedby);
					String uploadtime = (String)((Object[])woList.get(idx))[4];
					bean.setUploadTime(uploadtime);
					String woAll = (String)((Object[])woList.get(idx))[5];
					bean.setAllocationId(woAll);
					woBeanlist.add(bean);
					System.out.println("uploaded by : "+uploadedby+"  :: uploadtime :"+uploadtime);
					idx ++;
				}
			}
			String currTimeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			sqlWORetrieval="UPDATE DeviceDetail SET lastPollTime='"+currTimeStamp+"' , deviceStatus='Available' where name='"+dalRequest.getDeviceName()+"'";
			System.out.println("sqlWORetrieval ::"+sqlWORetrieval);
			sqlWORetrievalQuery = entityManager.createQuery(sqlWORetrieval);
			int i= sqlWORetrievalQuery.executeUpdate();
			/*
			
					Session session  = entityManager.unwrap(Session.class);
					//String queryStr = "UPDATE WorkorderAllocationDetail SET status.statusId=:statusId where allocationId=:allocationId";
					String queryStr =   "UPDATE DEVICEDETAIL SET LAST_POLL_TIME=:LAST_POLL_TIME where name=:name";
					org.hibernate.Query query = session.createQuery(queryStr);
					query.setParameter("LAST_POLL_TIME", currTimeStamp);
					query.setParameter("name", dalRequest.getDeviceName());
					int i=query.executeUpdate();

*/					System.out.println("i:: "+i);
			
		}catch(Exception e){
			e.printStackTrace();
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		dalResponse.getWoBean().addAll(woBeanlist);
		dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
		dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		return dalResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalRetrieveWorkorderResponse updateWorkorderStatus(
			DalRetrieveWorkorderRequest dalRequest) throws TagDalException {
		DalRetrieveWorkorderResponse dalResponse = new DalRetrieveWorkorderResponse();
		try{
			WorkorderAllocationHome allocationHome = new WorkorderAllocationHome(entityManager);
			String statusId = dalRequest.getStatusId();
			String allocationId = dalRequest.getAllocationId();
			allocationHome.updateWorkorderStatus(statusId,allocationId);
			
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(Exception e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		return dalResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalRetrieveWorkorderDetailResponse retrieveWorkorderDetials()
			throws TagDalException {
		Query sqlWORetrievalQuery = null;
		List woList     = null;
		List<WorkorderList> woBeanlist = new ArrayList<WorkorderList>();
		DalRetrieveWorkorderDetailResponse dalResponse = new DalRetrieveWorkorderDetailResponse();
		try{
			String queryStr = "select WA " +
					"from WorkOrderDetail WO, WorkorderAllocationDetail WA,DeviceDetail DD " +
					"where DD.deviceId=WA.deviceDetail.deviceId and WA.workorder.workorderId=WO.workorderId";
			sqlWORetrievalQuery = entityManager.createQuery(queryStr);
			System.out.println("sqlWORetrievalQuery  :: "+sqlWORetrievalQuery);
			woList=sqlWORetrievalQuery.getResultList();	
			int idx =0;
			if ((woList!=null)&&(woList.size()>0)){
				while (idx<woList.size()){
					WorkorderList  bean = new WorkorderList();
					WorkorderAllocationDetail allBean =(WorkorderAllocationDetail)woList.get(idx);
					bean.setWorkorderId(allBean.getWorkorder().getWorkorderId());
					bean.setAllocationId(allBean.getAllocationId());
					bean.setWorkorderName(allBean.getWorkorder().getName());
					bean.setStatusId(allBean.getStatus().getStatusId());
					bean.setDeviceName(allBean.getDeviceDetail().getName());
					
					idx++;
					woBeanlist.add(bean);
				}
			}	
			dalResponse.getWorkorderList().addAll(woBeanlist);
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(Exception e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		return dalResponse;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalUpdateResultsResponse updateWorkorderResults(
			DalUpdateResultsRequest dalRequest) throws TagDalException {
		DalUpdateResultsResponse dalResponse  = new DalUpdateResultsResponse();
		try{
			WorkorderAllocationHome allocationHome = new WorkorderAllocationHome(entityManager);
			byte[] resultsByte = dalRequest.getResultByte();
			String allocationId = dalRequest.getAllocationId();
			allocationHome.updateWorkorderResults(resultsByte,allocationId);
			
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(Exception e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		return dalResponse;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public DalDownloadResultsResponse retrieveResults(
			DalDownloadResultsRequest dalRequest) throws TagDalException {
		DalDownloadResultsResponse dalResponse = new DalDownloadResultsResponse();
		try{
			WorkorderAllocationHome allocationHome = new WorkorderAllocationHome(entityManager);
			WorkorderAllocationDetail woDetail = allocationHome.findById(dalRequest.getAllocationId());
			if(woDetail== null || woDetail.getResult()== null){
				throw new TagDalException(Message.DAL_RESULT_NOT_FOUND_CODE,Message.DAL_RESULT_NOT_FOUND_DESC);
			}
			dalResponse.setResultByte(woDetail.getResult());
			dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
			dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		}catch(TagDalException e){
			throw new TagDalException(Message.DAL_RESULT_NOT_FOUND_CODE,Message.DAL_RESULT_NOT_FOUND_DESC);
		}
		catch(Exception e){
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		return dalResponse;
	}
	public WorkOrderDetail getWorkOrderDetail(int workOrderId){
		WorkorderDetailHome workorderDetailHome = new WorkorderDetailHome( entityManager );
		return workorderDetailHome.findById(workOrderId);
	}

	@Override
	public String retrieveWorkorderReferenceName(String id) {
		WorkorderDetailHome home=new WorkorderDetailHome(entityManager);
		
		return home.findById(id).getReferenceName();
	}

	@Override
	public  List<WorkOrderDetail> retriveDashboardDetails() {
		WorkorderDetailHome home=new WorkorderDetailHome(entityManager);
		return home.WorkorderList();
	}

	@Override
	public DalRetrieveWordkorderDeviceDetails retrieveWorkorderDeviceDetails(String workorderId) {

		Query sqlWORetrievalQuery = null;
		List woList     = null;
		List<DeviceDetailsListBean> deviceDetailsList=new ArrayList<DeviceDetailsListBean>();
		List<WorkorderDetailBean> woBeanlist = new ArrayList<WorkorderDetailBean>();
		DalRetrieveWordkorderDeviceDetails dalResponse=new DalRetrieveWordkorderDeviceDetails();
		
		//String sqlWORetrieval= "select WO.referenceName,WO.name,WO.workorderId,WO.uploadedby,WO.uploadtime,WA.allocationId from WorkOrderDetail WO,WorkorderAllocationDetail WA, DeviceDetail DD,AgentDetail A where " +
		//"A.name='"+dalRequest.getAgentName()+"' and DD.name ='"+ dalRequest.getDeviceName()+"' and A.agentId = DD.agent.agentId and DD.deviceId=WA.deviceDetail.deviceId and WA.workorder.workorderId=WO.workorderId and WA.status.statusId=1 ORDER BY WA.allocationDate";
		
		String sqlWORetrieval="select WA.deviceDetail.name, WA.deviceDetail.location , WA.status.statusName, WA.allocationId  "
				+ "from WorkorderAllocationDetail WA,DeviceDetail DD where WA.deviceDetail.deviceId=DD.deviceId and "
				+ "WA.workorder.workorderId="+workorderId;
		try{
			sqlWORetrievalQuery = entityManager.createQuery(sqlWORetrieval);
			woList=sqlWORetrievalQuery.getResultList();	
			System.out.println("woList check size :: "+(woList.size()));
			int idx =0;
			
			if ((woList!=null)&&(woList.size()>0)){
				while (idx<woList.size()){
					DeviceDetailsListBean bean=new DeviceDetailsListBean();
					String deviceName =(String)((Object[])woList.get(idx))[0];
					String location = (String)((Object[])woList.get(idx))[1];
					String status = (String)((Object[])woList.get(idx))[2];
					String allocationid = (String)((Object[])woList.get(idx))[3];
					
					bean.setDeviceName(deviceName);
					bean.setLocation(location);
					bean.setAllocationId(allocationid);
					bean.setStatus(status);
					System.out.println("deviceName : "+deviceName+"  :: location :"+location+":: status : "+status+"  :: allocationid :"+allocationid);
					deviceDetailsList.add(bean);
					
					idx ++;
				}
				dalResponse.setDeviceDetailsList(deviceDetailsList);
			}
		}catch(Exception e){
			e.printStackTrace();
			dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
		
		//dalResponse.getDeviceDetailsList().addAll(deviceDetailsList);
		dalResponse.setCode(Message.DAL_SINGLE_OPERATION_SUCCESS_CODE);
		dalResponse.setDescription(Message.DAL_SINGLE_OPERATION_SUCCESS_DESC);
		return dalResponse;
	
		//return null;
	}

	@Override
	public String retrieveWorkordersLastPollTimedOut() throws TagDalException {
		// TODO Auto-generated method stub
		List<WorkorderDetailBean> woBeanlist = new ArrayList<WorkorderDetailBean>();
		 String currTimeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 System.out.println("currTimeStamp ::"+currTimeStamp);
		Calendar c = Calendar.getInstance();
		currTimeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(c.getTime());
		//c.add(Calendar.HOUR, -36);
		c.add(Calendar.MINUTE, -15);
		String timeinterval=new SimpleDateFormat("yyyyMMddhhmmss").format(c.getTime());
		System.out.println("timeinterval :: "+timeinterval +" :: currTimeStamp ::"+currTimeStamp);
		String sqlWORetrieval="select WA.deviceDetail.name, WA.deviceDetail.location , WA.status.statusName, WA.allocationId  "
				+ "from WorkOrderDetail WO, WorkorderAllocationDetail WA,DeviceDetail DD where WA.deviceDetail.deviceId=DD.deviceId and "
				+ "WA.workorder.workorderId=";
		sqlWORetrieval= "select distinct WA.deviceDetail.deviceId from WorkOrderDetail WO, WorkorderAllocationDetail WA,DeviceDetail DD  where WO.lastPollTime>"+timeinterval+" and WO.lastPollTime<"+currTimeStamp+" and WO.workorderId=WA.workorder.workorderId and  WA.deviceDetail.deviceId=DD.deviceId and DD.deviceStatus!='Inactive'";
		//sqlWORetrieval= "select DD.deviceId from DeviceDetail DD  where DD.lastPollTime>"+timeinterval+" and DD.lastPollTime<"+currTimeStamp+" and  DD.deviceStatus!='Inactive'";
		sqlWORetrieval= "select DD.deviceId from DeviceDetail DD  where DD.lastPollTime<"+timeinterval+"  and  DD.deviceStatus!='Inactive'";
		
		System.out.println("sqlWORetrieval :: "+sqlWORetrieval);
		List woList     = null;
		try{
			Query sqlWORetrievalQuery = entityManager.createQuery(sqlWORetrieval);
			woList=sqlWORetrievalQuery.getResultList();	
			System.out.println("woList check size for device status:: "+(woList.size()));
			int idx =0;
			
			if ((woList!=null)&&(woList.size()>0)){
				while (idx<woList.size()){
					String deviceid =(String)woList.get(idx++);
					sqlWORetrieval="UPDATE DeviceDetail SET deviceStatus='Inactive' where deviceId='"+deviceid+"'";
					System.out.println("updating status Inactive for device id : "+deviceid);
					System.out.println("sqlWORetrieval : "+sqlWORetrieval);
					sqlWORetrievalQuery = entityManager.createQuery(sqlWORetrieval);
					System.out.println("sqlWORetrievalQuery ::"+sqlWORetrievalQuery.executeUpdate());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//dalResponse.setCode(Message.DAL_WARNING_CODE_RESPONSE);
			//dalResponse.setDescription(Message.DAL_WARNING_DESC_RESPONSE);
		}
	
		return null;
	}

}

