package com.accenture.tag.file.uploader.utility;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipFile {

	static final int BUFFER = 2048;

	public static void createZip(String path, java.util.List<String> fileList) {
		try {
			// File zipfile=new
			// File("C:\\Pradnya\\Installables\\apache-tomcat-7.0.69-windows-x86\\apache-tomcat-7.0.69\\webapps\\data\\myfigs.zip");
			//File zipfile = new File("C:\\Pradnya\\workorder\\TestSuit.zip");
			File zipfile = new File(path+"/TestSuit.zip");
			zipfile.setExecutable(true,false);
			zipfile.setReadable(true, false);
			zipfile.setWritable(true, false);
        	
			fileList.add("TestSuit.zip");
			fileList.add("test.xml");
			if (!zipfile.exists())
				zipfile.createNewFile();
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(zipfile);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			// out.setMethod(ZipOutputStream.DEFLATED);
			byte data[] = new byte[BUFFER];
			// get a list of files from current directory
			File f = new File(path);
			String files[] = f.list();

			for (int i = 0; i < files.length; i++) {
				if (fileList.contains(files[i])) {
					if(files[i].equals("TestSuit.zip"))
						continue;
					System.out.println("Adding: " + files[i]);
					FileInputStream fi = new FileInputStream(path+"/" + files[i]);
					origin = new BufferedInputStream(fi, BUFFER);
					ZipEntry entry = new ZipEntry(files[i]);
					out.putNextEntry(entry);
					int count;
					while ((count = origin.read(data, 0, BUFFER)) != -1) {
						out.write(data, 0, count);
					}
					origin.close();
					}
				}
				out.close();
			//}
				for (int i = 0; i < files.length; i++) {
					File ff=new File(path+"/"+files[i]);
					if(ff.getName().equals("TestSuit.zip"))
						continue;
					System.out.println("deleting file "+ff.getName()+"  status "+ff.delete());
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
