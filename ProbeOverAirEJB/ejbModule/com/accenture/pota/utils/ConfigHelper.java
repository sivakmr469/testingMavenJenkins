package com.accenture.pota.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigHelper {
	
	private Properties prop = new Properties();
	InputStream input = null;


	public ConfigHelper(String fileName) {
		super();
		try {
			 File initialFile = new File(fileName);
			    input = new FileInputStream(initialFile);
			
			if (input == null) {
				return;
			}
			prop.load(input);
			
		} catch (IOException e) {
			
		}finally{
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	public String getString(String propertyKey){
		Enumeration<?> propMap ;
		propMap = prop.propertyNames();
		if(propMap != null){
			while (propMap.hasMoreElements()) {
				String key = (String) propMap.nextElement();
				if(propertyKey.equalsIgnoreCase(key)){
					return prop.getProperty(key);
				}
				
			}
		}
		return null;
		
	}
	
	

}
