package com.accenture.pota.dal.utils;

public interface Message {
	public static final String DAL_MANDATORY_PARAMS_ERROR_CODE = "610";
	public static final String DAL_NOT_UNIQUE_VALUE_ERROR_CODE = "3100";
	
	
	public static final String DAL_MANDATORY_PARAMS_ERROR_DESC = "Request is not valorized properly";
	public static final String DAL_NOT_UNIQUE_VALUE_ERROR_DESC = "Duplicated value: ";	

	public static final String DAL_SINGLE_OPERATION_SUCCESS_CODE = "100";
	public static final String DAL_SINGLE_OPERATION_SUCCESS_DESC = "Request Successfully executed";	
	
	public static final String DAL_OK_CODE_RESPONSE = "400";
	public static final String DAL_OK_DESC_RESPONSE = "Operation successfully executed";
	
	public static final String DAL_WARNING_CODE_RESPONSE = "600";
	public static final String DAL_WARNING_DESC_RESPONSE = "Request hasn't been completely executed";
	
	public static final String DAL_UNHANDLED_ERROR_CODE = "640";
	public static final String DAL_UNHANDLED_ERROR_DESC = "An error occurred during: {0}";
	
	public static final String DAL_INSERT_AGENT_GENERIC_ERROR_CODE = "1360";
	public static final String DAL_INSERT_AGENT_GENERIC_ERROR_DESC = "An error occurred during insert Agent";
	
	public static final String DAL_INSERT_WORKORDER_GENERIC_ERROR_CODE = "1361";
	public static final String DAL_INSERT_WORKORDER_GENERIC_ERROR_DESC = "An error occurred during insert probe";
	
	public static final String DAL_REGISTER_DEVICE_GENERIC_ERROR_CODE = "1362";
	public static final String DAL_REGISTER_DEVICE_GENERIC_ERROR_DESC = "An error occurred during device registration";
	
	public static final String DAL_AGENT_NOT_FOUND_CODE = "1363";
	public static final String DAL_AGENT_NOT_FOUND_DESC = "Agent not found";
	
	public static final String DAL_INSERT_WORKORDER_ALLOCATION_GENERIC_ERROR_CODE = "1364";
	public static final String DAL_INSERT_WORKORDER_ALLOCATION_GENERIC_ERROR_DESC = "An error occurred during insert probe allocation";
	
	public static final String DAL_RESULT_NOT_FOUND_CODE = "1365";
	public static final String DAL_RESULT_NOT_FOUND_DESC = "No results available";
	
	public static final String  PROBE_CREATION_SUCCESS_DESC="Probe Submitted for Execution, please visit Dashboard page for more details";
}
