package com.accenture.pota.sdk.http.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accenture.pota.dal.model.WorkOrderDetail;
import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.tag.file.uploader.utility.Person;
import com.accenture.tag.file.uploader.utility.RetrieveDetails;

@SuppressWarnings("serial")
@WebServlet(description = "Test servlet showing how to output json in Java", urlPatterns = { "/dashboarddetails" })
public class DashboardDetailsServlet extends HttpServlet{

	
	@EJB
	private TagSdkFacadeLocal tagSdkFacade;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			
			HttpSession session=req.getSession();
			 //List<WorkOrderDetail> woLIst= RetrieveDetails.retriveDashboardDetails();
			 List<WorkOrderDetail> woLIst=tagSdkFacade.retriveDashboardDetails();
			session.setAttribute("woLIst",woLIst);
			System.out.println("**************11111**************");
			resp.sendRedirect("/ProbeOverAirWeb/Dashboard_details.jsp");
			}catch(Exception e)
			{
			e.printStackTrace();
			}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
