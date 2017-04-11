package com.accenture.pota.dal.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public class WorkorderDetailHome {
	@PersistenceContext
	private EntityManager entityManager;

	public WorkorderDetailHome( EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	public void persist(WorkOrderDetail transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public WorkOrderDetail findById(Integer id) {
		try {
			WorkOrderDetail instance = entityManager.find(WorkOrderDetail.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WorkOrderDetail findById(String id) {
		try {
			WorkOrderDetail instance = entityManager.find(WorkOrderDetail.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WorkOrderDetail findByName(String name) {
		try {
			WorkOrderDetail instance = entityManager.find(WorkOrderDetail.class, name);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public List<WorkOrderDetail> WorkorderList() {
		try {
			
			Session session  = entityManager.unwrap(Session.class);
			@SuppressWarnings("unchecked")
			List<WorkOrderDetail> woList = session.createCriteria( WorkOrderDetail.class ).list();
			System.out.println("deviceList in ejb : "+woList);
			for (int i=0;i<woList.size();i++) {
				WorkOrderDetail wo =(WorkOrderDetail) woList.get(i);
				System.out.println(
						"name: "+wo.getName()+" :: uploaded by :"+wo.getUploadedby()+" :: upload time :"+wo.getUploadtime());
			}
			return woList;	    	
	
		//	return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

}
