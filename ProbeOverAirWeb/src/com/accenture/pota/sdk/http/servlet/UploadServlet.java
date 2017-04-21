package com.accenture.pota.sdk.http.servlet;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import org.eclipse.core.resources.ResourcesPlugin;

import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.entity.management.utils.TagEntityManagementMessage;
import com.accenture.tag.file.upload.bean.DownloadResultsRequest;
import com.accenture.tag.file.upload.bean.DownloadResultsResponse;
import com.accenture.tag.file.upload.bean.RetrieveWorkorderDetailsResponse;
import com.accenture.tag.file.upload.bean.WorkorderList;
import com.accenture.tag.file.uploader.http.delegate.TagDelegate;
import com.accenture.tag.file.uploader.utility.Constants;
import com.accenture.tag.file.uploader.utility.CreateXMLFile;
import com.accenture.tag.file.uploader.utility.CreateZipFile;
import com.accenture.tag.file.uploader.utility.Person;
import com.accenture.tag.file.uploader.utility.Probe;
import com.accenture.tag.file.uploader.utility.RestAPICall;
import com.accenture.tag.file.uploader.utility.RetrieveDetails;
import com.accenture.tag.file.uploader.utility.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;
	public byte[] bFile;

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		HttpSession session = request.getSession();
		String probename = "";
		String[] priority = new String[10];
		String[] FileNames = new String[10];
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(filePath));
		// Location to save data that is larger than maxMemSize.
		//factory.setRepository(new File("C:\\temp\\"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		int prioritylenght = 0, fileNemeindex = 0;
		List<String> fileList = new ArrayList<String>();
		List<String> deviceList = new ArrayList<String>();
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
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					FileNames[fileNemeindex++] = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
					fileList.add(fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length()));
					// out.println("Uploaded Filename: " + fileName + "<br>");
				} else {

					if (fi.getFieldName() != null && fi.getFieldName().equals("probename")) {
						probename = fi.getString();
						System.out.println("probename :: " + probename);
					} else if (fi.getFieldName() != null && fi.getFieldName().equals("priority"))
						priority[prioritylenght++] = fi.getString();
					else if (fi.getFieldName() != null && fi.getFieldName().equals("devicelist")) {
						if (!deviceList.contains(fi.getString()))
							deviceList.add(fi.getString());
						System.out.println("size :: " + fi.getSize() + "   :: name :: " + fi.getString() + " :: name :::");
					}
				}
				if (fi.getFieldName() != null && fi.getFieldName().equals("browsee")) {
					System.out.println("browseee :: " + fi.getFieldName());
				}
			}
			Probe poto = new Probe();
			poto.setProbeName(probename);
			Map<String, String> scriptmap = new HashMap<String, String>();
			for (int k = 0; k < fileList.size(); k++) {
				// System.out.println("FileName :: " + fileList.get(k) + " ::
				// priority ::" + priority[k]);
				scriptmap.put(fileList.get(k), priority[k]);
			}
			for (int k = 0; k < deviceList.size(); k++) {
				System.out.println("devicename ::  " + deviceList.get(k));

			}
			poto.setScriptsMap(scriptmap);
			poto.setFilepath(filePath);
			poto.setLstDevices(deviceList);
			CreateXMLFile.createXMLFIle(poto, filePath);
			CreateZipFile.createZip(filePath, fileList);
			String status = RestAPICall.callRestAPI(poto);
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

		} catch (Exception ex) {
			// System.out.println(ex);
			ex.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		this.doPost(request, response);
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