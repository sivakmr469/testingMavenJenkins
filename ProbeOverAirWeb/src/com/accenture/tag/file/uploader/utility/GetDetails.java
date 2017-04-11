package com.accenture.tag.file.uploader.utility;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.json.JSONException;
 
/**
 * Servlet implementation class GetDetails
 */
@WebServlet("/javaonline/GetDetails")
public class GetDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDetails() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("application/json;charset=UTF-8"); 
 
        response.setHeader("Cache-Control", "no-cache"); 
 
        //get the PrintWriter object to write the html page
        PrintWriter out = response.getWriter();
 
    String jsonData="";
 
        try {
 
          GetDetailsDAO getDetailsDAO=new GetDetailsDAO();
 
           jsonData = getDetailsDAO.getJsonData();
 
           out.print(jsonData);
 
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.close();
        }
 
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	this.doGet(request, response);
    }
 
}