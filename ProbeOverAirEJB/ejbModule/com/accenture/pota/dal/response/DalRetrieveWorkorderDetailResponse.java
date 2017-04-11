package com.accenture.pota.dal.response;

import java.util.ArrayList;
import java.util.List;

import com.accenture.pota.utils.WorkorderList;

public class DalRetrieveWorkorderDetailResponse extends DalRespo{
	
	/**
	 * 
	 */
	// private static final long serialVersionUID  = -221954301055600758L;
	List<WorkorderList> workorderList;

	/**
	 * @return the workorderList
	 */
	public List<WorkorderList> getWorkorderList() {
		if(workorderList == null){
			workorderList = new ArrayList<WorkorderList>();
		}
		return workorderList;
	}

}
