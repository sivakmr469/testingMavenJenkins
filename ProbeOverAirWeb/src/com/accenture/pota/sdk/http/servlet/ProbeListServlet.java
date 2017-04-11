package com.accenture.pota.sdk.http.servlet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accenture.tag.file.uploader.utility.Person;
import com.accenture.tag.file.uploader.utility.RetrieveDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
@SuppressWarnings("serial")
@WebServlet(description = "Test servlet showing how to output json in Java", urlPatterns = { "/dashboard" })
public class ProbeListServlet extends HttpServlet{
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try{
			
			HttpSession session=req.getSession();
			Person person = RetrieveDetails.retrivedtails();
			//person.setScriptsPriorityMap(scriptmap);
			String jSonResp = convertJavaObjectToJSon(person);
			System.out.println("jSonRequest  :: " + jSonResp);
			resp.setContentType("application/json");
			resp.getWriter().write(jSonResp);
			session.setAttribute("jSonResp", jSonResp);
		/*	List<String> json_obj =new ArrayList<String>();
			json_obj.add("awss");
			json_obj.add("sczsczxc");
			json_obj.add("zxczxcxc");
			json_obj.add("zxczxc");
			json_obj.add("zxczxc");
			
			req.setAttribute("json_obj",json_obj);
			resp.sendRedirect("/ProbeOverAirWeb/device_avail.jsp");
		*/}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	} 
	private static Gson gsonObject = new Gson();
	private static String convertJavaObjectToJSon(final Object javaObject) {

		JsonSerializer<Date> ser = new JsonSerializer<Date>() {
			@Override
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				return src == null ? null : new JsonPrimitive(src.getTime());
			}
		};

		gsonObject = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
		String jsonStringObject = gsonObject.toJson(javaObject);

		return jsonStringObject;
	}

}
