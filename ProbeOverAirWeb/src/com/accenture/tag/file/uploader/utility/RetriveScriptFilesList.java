package com.accenture.tag.file.uploader.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.accenture.pota.sdk.xml.model.ScriptList;
import com.accenture.pota.utils.WorkorderList;

public class RetriveScriptFilesList {
	static final int BUFFER = 2048;
	private static final int BUFFER_SIZE = 4096;

	public static void main(String argv[]) {
		try {
			String probeName="myprobe5";
			WorkorderList woList=new WorkorderList();
			woList.setWorkorderName("myprobe5");
			
			System.out.println("result :: "+probeName.equalsIgnoreCase(woList.getWorkorderName()));
			//unzip("C:\\JBOSS\\jboss-eap-7.0\\bin\\workorder\\workorder20160705020943myprobe5.zip", "c:\\pradnya");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<ScriptList>  unzip(String zipFilePath, String destDirectory) throws IOException {
		
		//destDirectory="c:\\Pradnya\\workorder\\temp";
		
		destDirectory=Constants.EXTRACT_UPLOAD_FILE_PATH;
		List<ScriptList> list=new ArrayList<ScriptList>();
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
				list=	extractFile(zipIn, filePath);
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
		
		return list;
	}

	private static List<ScriptList>  extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		String temppath="/usr/local/share/jboss/bin/workorder/extract/";
		List<ScriptList> list=new ArrayList<ScriptList>();
		File tempfile=new File(filePath);
		
		if(!tempfile.exists())
			tempfile.createNewFile();
		tempfile.setReadable(true, false);
		tempfile.setExecutable(true, false);
		tempfile.setWritable(true, false);
		FileOutputStream fos=new FileOutputStream(tempfile);
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
		list= ReadXMLFile(temppath+"test.xml");
		//FileUtils.deleteDirectory(tempfile);
		File f = new File(temppath);
		String files[] = f.list();
		/*for (int i = 0; i < files.length; i++) {
			File ff=new File(temppath+"/"+files[i]);
			System.out.println("deleting file "+ff.getName()+"  status "+ff.delete());
		
		}*/
		return list;
	}

	private static List<ScriptList>  ReadXMLFile(String filepath) {
		ProbeDetailsBean bean=new ProbeDetailsBean(); 
		List<String> lstFiles = new ArrayList<String>();
		List<ScriptList> list=new ArrayList<ScriptList>();
		File fXmlFile = new File(filepath);
		fXmlFile.setReadable(true, false);
		fXmlFile.setExecutable(true, false);
		fXmlFile.setWritable(true, false);
		
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
					ScriptList script=new ScriptList();
					System.out.println("File Name: " + eElement.getAttribute("name"));
					script.setFileName(eElement.getAttribute("name"));
					script.setPriority(eElement.getAttribute("Priority"));
					list.add(script);

				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setLstFileName(lstFiles);
		return list;
	}
}
