package com.accenture.pota.sdk.http.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Hi");
		String username=req.getParameter("username");
		String password=req.getParameter("userpassword");
		
		System.out.println("username :: "+username+"  ::  password "+password);
		if(username!=null && password!=null)
		{
			if(username.equals("Admin") && password.equals("Apple!23"))
			{
				
				System.out.println("redirecting");
				resp.sendRedirect("/ProbeOverAirWeb/device_avail.html");
			}else
				resp.sendRedirect("/ProbeOverAirWeb/loginerr.html");
				
		}
		else
			resp.sendRedirect("/ProbeOverAirWeb/loginerr.html");
		
		System.out.println("done");
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
}
