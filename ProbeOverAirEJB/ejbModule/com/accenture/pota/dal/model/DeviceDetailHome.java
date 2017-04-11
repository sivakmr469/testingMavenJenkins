package com.accenture.pota.dal.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.utils.Device;

public class DeviceDetailHome {
	@PersistenceContext
	private EntityManager entityManager;

	public DeviceDetailHome( EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	public void persist(DeviceDetail transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	public List findByFilter(Device device) {
		try {
			Session session  = entityManager.unwrap(Session.class);
			Criteria crit = session.createCriteria( DeviceDetail.class );
			crit.add( Restrictions.eq( "name", device.getDeviceName() ) );
			return crit.list();	    	
		} 
		catch ( RuntimeException re ) {
			throw re;
		}
	}
	//----------------------------------------------------------------------------------------
	@SuppressWarnings("rawtypes")
	public List findAll() {
		try {
			Session session  = entityManager.unwrap(Session.class);
			List deviceList = session.createCriteria( DeviceDetail.class ).list();
			System.out.println("deviceList in ejb : "+deviceList);
			for (int i=0;i<deviceList.size();i++) {
				DeviceDetail device =(DeviceDetail) deviceList.get(i);
				System.out.println(
						"Device ID :: " + device.getDeviceId() + "  :: devicename  :: " + device.getName());
			}
			return deviceList;	    	
		} 
		catch ( RuntimeException re ) {
			throw re;
		}
	}
	
}
