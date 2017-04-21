package com.accenture.pota.sdk.http.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accenture.tag.file.upload.bean.DownloadResultsRequest;
import com.accenture.tag.file.upload.bean.DownloadResultsResponse;
import com.accenture.tag.file.uploader.http.delegate.TagDelegate;
import org.apache.commons.codec.binary.Base64;

import com.accenture.tag.file.uploader.utility.Constants;
import com.accenture.tag.file.uploader.utility.Utils;
@SuppressWarnings("serial")
@WebServlet(description = "Test servlet showing how to output json in Java", urlPatterns = { "/downloadReport" })
public class DownloadReportServlet  extends HttpServlet{
	public static byte[] bFile;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= req.getSession();
		String allocationId=req.getParameter("allocationId");
		System.out.println("allocationId :: "+allocationId);
		resp.setContentType("text/plain");
		resp.setHeader("Content-Disposition",
	                     "attachment;filename=downloadname.txt");
		
		TagDelegate tagDelegate=new TagDelegate();
		DownloadResultsRequest downReq = new DownloadResultsRequest();
		downReq.setAllocationId(allocationId);
		System.out.println("download request :: "+downReq);
		DownloadResultsResponse downResp = new DownloadResultsResponse();
		downResp = tagDelegate.downloadResults(downReq);
		OutputStream os = resp.getOutputStream();
		if (downResp != null) {
			if (Constants.TAG_SUCCESS.equalsIgnoreCase(downResp.getResultCode())) {
				bFile = downResp.getResultByte();
				byte[] decodeBase64 = Base64.decodeBase64(bFile);
				String filePath = "c:\\Pradnya";
				FileOutputStream fileOuputStream = null;
				if (Utils.isNotEmptyNull(filePath)) {
					String filepath = filePath + "\\" + "Results_" + System.currentTimeMillis() + ".txt";
					try {
						System.out.println("Uploading resut to JBOSS");
						fileOuputStream = new FileOutputStream(filepath);
						os.write(decodeBase64);
						fileOuputStream.close();
						System.out.println("Result file was successfully downloaded.");

					} catch (Exception e1) {
						System.out.println("Error occured while downloading results");
						e1.printStackTrace();
					}
				}
			}
		}
				os.flush();os.close();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

}
