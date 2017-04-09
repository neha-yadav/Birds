package com.request.model;

public interface BirdsDAO {

	
	public void insert(Birds obj[])throws Exception;
	public void selectAll()throws Exception;
	public void delete(String id)throws Exception;
	public void select(String id)throws Exception;
	
}
