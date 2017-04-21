package com.accenture.pota.sdk.http.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.accenture.pota.utils.WorkorderList;
import com.accenture.tag.file.uploader.utility.ProbeDetailsBean;


public class Zip {
	static final int BUFFER = 2048;
	private static final int BUFFER_SIZE = 4096;

	public static void main(String argv[]) {
		try {/*
			String probeName="myprobe5";
			WorkorderList woList=new WorkorderList();
			woList.setWorkorderName("myprobe5");
			
			System.out.println("result :: "+probeName.equalsIgnoreCase(woList.getWorkorderName()));
			//unzip("C:\\JBOSS\\jboss-eap-7.0\\bin\\workorder\\workorder20160705020943myprobe5.zip", "c:\\pradnya");
		*/
			String currTimeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
			System.out.println("Execution Time : " + currTimeStamp);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ProbeDetailsBean  unzip(String zipFilePath, String destDirectory) throws IOException {
		
		//destDirectory="c:\\Pradnya\\workorder\\temp";
		//destDirectory="\\usr\\local\\share\\jboss\\bin\\workorder\\temp\\";
		destDirectory= com.accenture.tag.file.uploader.utility.Constants.UPLOAD_FILE_PATH;
		
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		destDir.setReadable(true, false);
		destDir.setExecutable(true, false);
		destDir.setWritable(true, false);
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		ProbeDetailsBean bean=new ProbeDetailsBean();
		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			System.out.println("filePath : "+filePath);
			
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				bean=	extractFile(zipIn, filePath);
				//break;
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
		
		return bean;
	}

	private static ProbeDetailsBean  extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		String temppath="c:\\Pradnya\\workorder\\temp";
		temppath= com.accenture.tag.file.uploader.utility.Constants.UPLOAD_FILE_PATH;
		File tempfile=new File(temppath);
		if(!tempfile.exists())
			tempfile.mkdirs();
		tempfile.setReadable(true, false);
		tempfile.setExecutable(true, false);
		tempfile.setWritable(true, false);
		FileOutputStream fos=new FileOutputStream(filePath);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	/*	if(filePath.contains("test.xml"))
		continue;
	*/
		ProbeDetailsBean bean= ReadXMLFile(temppath+"\\test.xml");
		//FileUtils.deleteDirectory(tempfile);
		return bean;
	}

	private static ProbeDetailsBean ReadXMLFile(String filepath) {
		ProbeDetailsBean bean=new ProbeDetailsBean(); 
		List<String> lstFiles = new ArrayList<String>();
		File fXmlFile = new File(filepath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Testname");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("File Name: " + eElement.getAttribute("name"));
					lstFiles.add(eElement.getAttribute("name"));

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setLstFileName(lstFiles);
		return bean;
	}
}