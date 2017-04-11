package com.accenture.tag.file.uploader.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.accenture.pota.dal.utils.Message;
import com.accenture.pota.entity.management.utils.TagEntityManagementMessage;
import com.accenture.tag.file.upload.bean.CreateWorkorderRequest;
import com.accenture.tag.file.upload.bean.CreateWorkorderResponse;
import com.accenture.tag.file.upload.bean.Device;
import com.accenture.tag.file.uploader.http.delegate.TagDelegate;
import com.accenture.tag.file.uploader.utility.Constants;
import com.accenture.tag.file.uploader.utility.Utils;

public class RestAPICall {
	

	public static byte[] bFile;
	
	public static String callRestAPI(Probe poto)
	{
		List<Device>devList = new ArrayList<Device>();

		String result="";
		String woName = poto.getProbeName();
		String filePath =poto.getFilepath(); 
	//	String deviceList = "RASPBERRYPI,LINUX";
		//boolean isValid = validateFields(woName, filePath, deviceList);
		
		/*String[] split = deviceList.split(Constants.DEVICE_DELIMITER);
		if(split != null){
			devList = new ArrayList<Device>();
			for (String deviceName : split) {
				Device dev = new Device();
				dev.setDeviceName(deviceName);
				devList.add(dev);
			}
		}
		*/
		List<String> lstDevice=poto.getLstDevices();
		System.out.println("in RestAPI device list size ::"+lstDevice.size());
		for (String deviceName : lstDevice) {
			Device dev = new Device();
			dev.setDeviceName(deviceName);
			
			if(!devList.contains(dev))
			{
				System.out.println("in RestAPI adding new device ::"+deviceName);
				devList.add(dev);
			}
		}
System.out.println("devList size before creating probe :: "+devList.size());
		
		//if(isValid){
			String fileNam = validateFile(filePath);
			if(Utils.isNotEmptyNull(fileNam) ){
				CreateWorkorderRequest request = new CreateWorkorderRequest();
				request.setDescription(poto.getProbeName()+"ok");
				request.setName(woName);
				request.setFileByte(bFile);
				request.setDeviceList(devList);
				request.setFileName(fileNam);
				TagDelegate tagDelegate = new TagDelegate();
				CreateWorkorderResponse createWorkorder = tagDelegate.createWorkorder(request);
				if(createWorkorder != null){
					//showResponseMessage(createWorkorder,parent);
					System.out.println("TAG code  ::"+createWorkorder.getResultCode());
					if(Constants.TAG_SUCCESS.equalsIgnoreCase(createWorkorder.getResultCode())){
						System.out.println("Successfully uploaded");
						result=createWorkorder.getResultCode();
					}else
					{
						System.out.println("error with code "+createWorkorder.getResultCode());
						
						/*if(TagEntityManagementMessage.DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_CODE.equalsIgnoreCase(createWorkorder.getResultCode()))
								{
									result=TagEntityManagementMessage.DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_DESC;
								}
						else*/
							result=createWorkorder.getResultCode();
					}
					
					
				}else{/*
					MessageBox messageBox =  new MessageBox(getParentShell(),  SWT.ICON_ERROR);
					messageBox.setText("Error!!!");
					messageBox.setMessage("Service unavailable");
				     messageBox.open();*/
					System.out.println("Service unavailable ......");
					result="Error";
				}	
			}
			
		//}
		
		
	return result;
	}
	private static String validateFile(String filePath) {
		String fileName = null;
		if(Utils.isNotEmptyNull(filePath) && !"Zip file Path Here".equalsIgnoreCase(filePath)){
			FileInputStream fileInputStream;
			//File file = new File(filePath);
			filePath="/usr/local/share/jboss/bin/workorder/temp/";
			File file = new File("/usr/local/share/jboss/bin/workorder/temp/TestSuit.zip");
			file.setReadable(true, false);
			file.setExecutable(true, false);
			file.setWritable(true, false);
			bFile = new byte[(int) file.length()];
			//MessageBox messageBox =  new MessageBox(getParentShell(),  SWT.ICON_ERROR);
			//messageBox.setText("Error!!!");
			
			try {
				fileInputStream = new FileInputStream(file);
				fileName = file.getName();
				fileInputStream.read(bFile);
				fileInputStream.close();
				System.out.println("fileName in validate file  ::"+fileName);
			} catch (FileNotFoundException e) {
				//messageBox.setMessage("File not found");
			    //messageBox.open();
				e.printStackTrace();
			    return null;
			}catch (IOException e) {
				//messageBox.setMessage("Error occured during file upload");
			    //messageBox.open();
				e.printStackTrace();
			    return null;
			}
		}else{
			return fileName;
		}
		return fileName;
	}

	




}
