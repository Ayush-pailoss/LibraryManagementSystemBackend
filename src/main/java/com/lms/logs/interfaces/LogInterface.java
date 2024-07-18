package com.lms.logs.interfaces;
public interface LogInterface {
	
	public String logInfoForUserAndAdmin(String message);
	public String logInfoForBooks(String message);
	
	default String logWarnForUserAndAdmin(String message) {
		return "something is not working properly";
	}
	default String logWarnForBooks(String message) {
		return "something is not working properly";
	}
}
