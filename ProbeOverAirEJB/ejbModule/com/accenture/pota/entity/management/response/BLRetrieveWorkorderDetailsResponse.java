package com.accenture.pota.entity.management.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.accenture.pota.utils.BLResponse;
import com.accenture.pota.utils.WorkorderList;

public class BLRetrieveWorkorderDetailsResponse extends BLResponse implements Serializable{

	/**
	 * 
	 */
	// private static final long serialVersionUID  = -4428447707310417277L;
	private List<WorkorderList> workorderList ;
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
