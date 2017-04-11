package com.accenture.tag.file.uploader.utility;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

/**
 * A simple example of showing how to output json in a Servlet.
 */
@WebServlet(description = "Test servlet showing how to output json in Java", urlPatterns = { "/test" })
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		response.setContentType("application/json");
		//response.setHeader("Cache-Control", "nocache");
        	//response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		JSONObject json = new JSONObject();

		// put some value pairs into the JSON object as into a Map.
		try {
			json.put("status", 200);
		json.put("msg", "OK");

		// put a "map" 
		JSONObject map = new JSONObject();
		map.put("key1", "val1");
		map.put("key2", "val2");
		json.put("map", map);
		
		// put an "array"
		JSONArray arr = new JSONArray();
		arr.put(5);
		arr.put(3);
		arr.put(1);
		json.put("arr", arr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// finally output the json string		
		out.print(json.toString());
	}
}
