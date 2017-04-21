package com.accenture.pota.dal.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AgentDetailHome {
	@PersistenceContext
	private EntityManager entityManager;

	public AgentDetailHome( EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	public void persist(AgentDetail transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public AgentDetail findById(Integer id) {
		try {
			AgentDetail instance = entityManager.find(AgentDetail.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List findAgentByName(String agentName){
		try {
			Session session  = entityManager.unwrap(Session.class);
			Criteria crit = session.createCriteria( AgentDetail.class );
			crit.add( Restrictions.eq( "name", agentName ) );
			return crit.list();	    	
		} 
		catch ( RuntimeException re ) {
			throw re;
		}
	}

}
