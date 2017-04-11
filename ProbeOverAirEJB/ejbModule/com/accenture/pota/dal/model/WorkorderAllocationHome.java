package com.accenture.pota.dal.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;

public class WorkorderAllocationHome {
	@PersistenceContext
	private EntityManager entityManager;

	public WorkorderAllocationHome( EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	public void persist(WorkorderAllocationDetail transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public WorkorderAllocationDetail findById(String string) {
		try {
			WorkorderAllocationDetail instance = entityManager.find(WorkorderAllocationDetail.class, string);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void updateWorkorderStatus(String statusId,String allocationId) {
		try{
			Session session  = entityManager.unwrap(Session.class);
			String queryStr = "UPDATE WorkorderAllocationDetail SET status.statusId=:statusId where allocationId=:allocationId";
			Query query = session.createQuery(queryStr);
			query.setParameter("statusId", statusId);
			query.setParameter("allocationId", allocationId);
			query.executeUpdate();
		}
		catch(RuntimeException e){
			throw e;
		}
		
	}

	public void updateWorkorderResults(byte[] resultsByte, String allocationId) {
		try{
			Session session  = entityManager.unwrap(Session.class);
			String queryStr = "UPDATE WorkorderAllocationDetail SET result=:resultsByte,status.statusId=:statusId where allocationId=:allocationId";
			Query query = session.createQuery(queryStr);
			query.setParameter("statusId", "3");
			query.setParameter("allocationId", allocationId);
			query.setParameter("resultsByte", resultsByte);
			query.executeUpdate();
		}
		catch(RuntimeException e){
			throw e;
		}
		
	}
	

}
