package com.request.model;

import javax.xml.ws.BindingType;

@BindingType
public class InsertResult {

	String status;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	String message;
	public InsertResult(String status,String message)
	{
		this.status=status;
		this.message=message;
	}
	
}
