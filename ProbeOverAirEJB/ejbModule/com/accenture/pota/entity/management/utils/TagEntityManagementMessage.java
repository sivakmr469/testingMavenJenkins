package com.accenture.pota.entity.management.utils;

public  interface TagEntityManagementMessage {
	
	public static final String MANDATORY_PARAMS_ERROR_CODE_REQUEST            = "3100001";
	public static final String MANDATORY_PARAMS_ERROR_DESC = "Missing mandatory parameter: ";
	public static final String FILE_SAVE_FAILURE_CODE            = "3100002";
	public static final String FILE_SAVE_FAILURE_DESC = "Error during upload file";
	public static final String DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_CODE            = "3100003";
	public static final String DEVICE_NOT_FOUND_BUT_WO_SUCCESSED_DESC = "Device not found but probe successfully uploaded";
	public static final String ERROR_WHILE_ALLOCATION_CODE            = "3100004";
	public static final String ERROR_WHILE_ALLOCATION_DESC = "Error occured while allocating probe";
	public static final String WORKORDER_NOT_FOUND_CODE = "3100005";
	public static final String WORKORDER_NOT_FOUND_DESC = "No probes found";
	public static final String WORKORDER_RETRIEVE_CODE = "3100006";
	public static final String WORKORDER_RETRIEVE_DESC = "Error occured during retrieving probe";
	public static final String UPLOAD_RESULTS_ERROR_CODE = "3100007";
	public static final String UPLOAD_RESULTS_ERROR_DESC = "Error occured during upload results";
	public static final String DOWNLOAD_RESULTS_ERROR_CODE = "3100008";
	public static final String DOWNLOAD_RESULTS_ERROR_DESC = "Error occured during download results";
	
	
}
