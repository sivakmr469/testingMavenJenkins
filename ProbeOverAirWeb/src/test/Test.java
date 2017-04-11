package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test {


	static final int BUFFER = 2048;
	public static void main(String[] args) {
		Test.createZip("/usr/local/share/jboss/bin/workorder/temp/");
	}

	public static void createZip(String path) {
		try {
		
			File zipfile = new File(path+"TestSuit.zip");
			File testfile = new File(path+"test.xml");
			List<String> fileList=new ArrayList<String>();
			fileList.add("TestSuit.zip");
			fileList.add("test.xml");
			if (!zipfile.exists())
			{
				zipfile.createNewFile();
				System.out.println(" TestSuit.zip File created...");
			}
			if (!testfile.exists())
			{
				testfile.createNewFile();
				System.out.println("test.xml File created...");
			}
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
					FileInputStream fi = new FileInputStream(path+ files[i]);
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
				
				/*for (int i = 0; i < files.length; i++) {
					File ff=new File(path+"\\"+files[i]);
					System.out.println("deleting file "+ff.getName()+"  status "+ff.delete());
				
				}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
