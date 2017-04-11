package com.accenture.tag.file.uploader.utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.entity.management.utils.TagEntityManagementMessage;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "My First Servlet", urlPatterns = { "/FirstServlet", "/FirstServlet.do" }, initParams = {
		@WebInitParam(name = "id", value = "1"), @WebInitParam(name = "name", value = "pankaj") })
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START = "<html><body>";
	public static final String HTML_END = "</body></html>";
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Date date = new Date();
		out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date=" + date + "</h3>" + HTML_END);
		//File tempdir=new File("C:\\Pradnya\\tempdir\\");
		File tempdir=new File(filePath);
		if(!tempdir.exists())
			tempdir.mkdir();

		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		// java.io.PrintWriter out = response.getWriter( );
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(tempdir);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		// out.print(request.getParameter("probe-name"));
		String isupload = request.getParameter("upload");
		String isremove = request.getParameter("remove");
		System.out.println("isupload :: "+isupload+" :: isremove :: "+isremove);
		if (isupload!=null && isupload.equals("true")) {
			

			try {
				// Parse the request to get file items.
				List fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						// Write the file
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
							//System.out.println("path ::  "+(tempdir+"/"  + fileName.substring(fileName.lastIndexOf("\\"))));
							//file = new File(tempdir + "\\"+fileName.substring(fileName.lastIndexOf("\\")));
						} else {
							file = new File(filePath  + fileName.substring(fileName.lastIndexOf("\\") + 1));
							//file = new File(tempdir + "\\"+fileName.substring(fileName.lastIndexOf("\\") + 1));
							//System.out.println("path ::"+(tempdir + fileName.substring(fileName.lastIndexOf("\\") + 1)));
						}
						if(!file.exists())
							file.createNewFile();
						
						fi.write(file);
						System.out.println("Uploaded Filename of local file: " + file.getName() );
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if(isupload!=null && isupload.equals("false"))
		{
			String probename=request.getParameter("probename");
			int prioritylenght=0;
			String[] priority = new String[10];
			List<String> deviceList = new ArrayList<String>();
			List<String> fileList = new ArrayList<String>();
			String[] fileNames = new String[50];
			String[] filepriority = new String[50];
			int fileNamesIndex=0,filepriorityIndex=0;
			try {
				// Parse the request to get file items.
				List fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator i = fileItems.iterator();

				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (fi.isFormField()) {
						if (fi.getFieldName() != null && fi.getFieldName().equals("probename")) {
							probename = fi.getString();
							System.out.println("probename :: " + probename);
						} else if (fi.getFieldName() != null && fi.getFieldName().equals("priority"))
						{
							priority[prioritylenght++] = fi.getString();
							filepriority[filepriorityIndex++]=fi.getString();
						}else if (fi.getFieldName() != null && fi.getFieldName().equals("devicelist")) 
						{
							if (!deviceList.contains(fi.getString()))
								deviceList.add(fi.getString());
							
						}
						else if (fi.getFieldName() != null && fi.getFieldName().equals("filelist")) 
						{
							if (!fileList.contains(fi.getString()))
								fileList.add(fi.getString());
							fileNames[fileNamesIndex++]=fi.getString();
						}
					}
					
				}
				
				
				
				List<FilePriority>  lstFilePriority=new ArrayList<FilePriority>();
				Probe newProbe = new Probe();
				
				
				for(int k=0;k<prioritylenght;k++)
				{
					System.out.println("File Name ["+k+"] :: " + fileNames[k] +" :: priority  ::"+filepriority[k]);
					FilePriority objFilePriority=new FilePriority();
					objFilePriority.setFileName(fileNames[k] );
					objFilePriority.setPriority(filepriority[k]);
					lstFilePriority.add(objFilePriority);
					
				}
				newProbe.setProbeName(probename);
				newProbe.setLstDevices(deviceList);
				newProbe.setLstFilePriority(lstFilePriority);
				newProbe.setFilepath(tempdir.getPath());
				CreateXMLFile.createXMLFIle(newProbe, tempdir.getPath());
				CreateZipFile.createZip(filePath, fileList);
				String status = RestAPICall.callRestAPI(newProbe);
				String msg = "";
				if (Constants.TAG_SUCCESS.equalsIgnoreCase(status)) {
					msg = Message.PROBE_CREATION_SUCCESS_DESC;
				} else if (TagEntityManagementMessage.DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_CODE.equalsIgnoreCase(status)) {
					msg = TagEntityManagementMessage.DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_DESC;
				} else if (TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_CODE_REQUEST.equalsIgnoreCase(status)) {
					msg = TagEntityManagementMessage.MANDATORY_PARAMS_ERROR_DESC;
				} else if (Message.DAL_NOT_UNIQUE_VALUE_ERROR_CODE.equalsIgnoreCase(status)) {
					msg = Message.DAL_NOT_UNIQUE_VALUE_ERROR_DESC + " Probe Name";
					session.setAttribute("status", msg);
				} else
					msg = status;

				session.setAttribute("status", msg);
				System.out.println("deviceList size :: " + deviceList.size());
				System.out.println("Probe Creation status   :: " + status);
				response.sendRedirect("/ProbeOverAirWeb/Probe_Creation_Status.jsp");
			}catch(Exception e)
			{e.printStackTrace();}
			//System.out.println("probename :: "+probename+" :: priority :: "+priority);
			
			
		}
		if(isremove!=null && isremove.equals("true"))
		{
			String filename=request.getParameter("filename");
			System.out.println("filename ::"+filename);
			String files[] = tempdir.list();
			System.out.println("deleting file "+filename);
			for (int i = 0; i < files.length; i++) {
				if(files[i].equals(filename))
				{
						File ff=new File(tempdir+"/"+filename);
						System.out.println("deleting file "+ff.getName()+"  status "+ff.delete());
				}else
				{
					System.out.println("invalid file");
				}
			}
		

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		this.doGet(request, response);
	}
}