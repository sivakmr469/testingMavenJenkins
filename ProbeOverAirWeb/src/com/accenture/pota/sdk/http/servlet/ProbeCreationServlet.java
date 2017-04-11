package com.accenture.pota.sdk.http.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accenture.pota.dal.bean.DalDeviceDetail;
import com.accenture.pota.dal.exception.TagDalException;
import com.accenture.pota.dal.facade.device.TagDalDeviceFacade;
import com.accenture.pota.dal.facade.device.TagDalDeviceFacadeLocal;
import com.accenture.pota.dal.response.DalRetrieveDeviceResponse;
import com.accenture.pota.entity.management.request.BLRegisterDeviceRequest;
import com.accenture.pota.sdk.facade.TagSdkFacadeLocal;
import com.accenture.pota.sdk.xml.model.RegisterDeviceResponse;

/**
 * Servlet implementation class ProbeCreationServlet
 */
@WebServlet("/createprobe")
public class ProbeCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProbeCreationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@EJB
	private TagSdkFacadeLocal tagSdkFacade;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// TagDalDeviceFacadeLocal facade=new TagDalDeviceFacade();
		try {
			HttpSession session=request.getSession();
			List<DalDeviceDetail> devlist = new ArrayList<>();//tagSdkFacade.retrieveDeviceList();

			/*
			 * DalRetrieveDeviceResponse devListresp =
			 * facade.retrieveDeviceList(); List<DalDeviceDetail> devList =
			 * devListresp.getDeviceList();
			 */ System.out.println("devList ::"
			 +devlist);
			 for (DalDeviceDetail device : devlist) {
				System.out.println(
						"Device ID :: " + device.getDeviceId() + "  :: devicename  :: " + device.getDeviceName() +" :: location ::"+device.getLocation()+" :: status :: "+device.getDeviceStatus());
			}
			 
			 session.setAttribute("deviceList", devlist);
			 response.sendRedirect("/ProbeOverAirWeb/Script.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
