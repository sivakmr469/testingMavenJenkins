package com.accenture.pota.dal.response;

import java.util.ArrayList;
import java.util.List;

import com.accenture.pota.dal.bean.WorkorderDetailBean;

public class DalRetrieveWorkorderResponse extends DalRespo {

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -6862696502117306180L;
	
	List<WorkorderDetailBean> woBean ;

	/**
	 * @return the woBean
	 */
	public List<WorkorderDetailBean> getWoBean() {
		if(woBean == null){
			woBean = new ArrayList<WorkorderDetailBean>();
		}
		return woBean;
	}
	
	
	

}
