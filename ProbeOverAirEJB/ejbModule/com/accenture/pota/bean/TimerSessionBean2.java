package com.accenture.pota.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.facade.workorder.TagDalWorkorderFacadeLocal;

/**
 * Session Bean implementation class TimerSessionBean
 */
@Stateless
@LocalBean
public class TimerSessionBean2 {
	
	@EJB
	private TagDalWorkorderFacadeLocal dbAccessWorkorder;

	@Schedule(second = "1,57", minute = "*", hour = "*")
	public void execute(Timer timer) {
		System.out.println("Executing ...");
		System.out.println("Execution Time : " + new Date());
		try {
			dbAccessWorkorder.retrieveWorkordersLastPollTimedOut();
		} catch (TagDalException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		
		}	
		System.out.println("____________________________________________");
	}
}
