package com.accenture.pota.sdk.http.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.tag.file.uploader.utility.Person;
import com.accenture.tag.file.uploader.utility.ProbeDetailsBean;
import com.accenture.tag.file.uploader.utility.RetrieveDetails;
@SuppressWarnings("serial")
@WebServlet(description = "Test servlet showing how to output json in Java", urlPatterns = { "/probedetails" })
public class ProbeDetailsServlet extends HttpServlet {

	@EJB
	private TagSdkFacadeLocal tagSdkFacade;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			
			HttpSession session=req.getSession();
			String filename=req.getParameter("probename");
			String probeName=req.getParameter("probeID");
			String refName=tagSdkFacade.retrieveWorkorderReferenceName(probeName);
			System.out.println("probeID:: "+probeName+" :: probeName:: "+filename);
			//ProbeDetailsBean bean= RetrieveDetails.retrivedtails(probeName);
			Person person=RetrieveDetails.retrivedtails(probeName,refName);;
			//session.setAttribute("fileList",bean.getLstFileName());
			//session.setAttribute("deviceList",bean.getLstdevices());
			session.setAttribute("probedetails",person);
			session.setAttribute("probeName",filename);
			
			session.setAttribute("fileList",person.getLstFileName());
			System.out.println("FileList in servlet :: "+person.getLstFileName());
			for( ProbeDetailsBean probe :person.getProbeList() )
			{
				System.out.print("devicename : "+probe.getDeviceName());
				System.out.print(" :: StatusID : "+probe.getStatusID());
				System.out.print(" :: Status : "+probe.getStatus());
				System.out.print(" :: Allocation : "+probe.getAllocationID());
			
			}
			System.out.println("1111");
			resp.sendRedirect("/ProbeOverAirWeb/device_avail_detail.jsp");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
}
