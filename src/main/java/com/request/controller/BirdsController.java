package com.request.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.request.model.Birds;
import com.request.model.BirdsJDBCImplementor;
import com.request.model.InsertResult;

;


@Controller
@RestController
public class BirdsController {

	@Resource
	BirdsJDBCImplementor result;
	@ResponseBody
	@RequestMapping(value="/birds" ,method=RequestMethod.POST)
	public InsertResult insertion( @Valid @RequestBody Birds req[],BindingResult InsertResult)
	{
		try
		{
		result.setDataSource();
		result.insert(req);
		if(InsertResult.hasErrors())
			throw new Exception();
		else
		return new InsertResult("201","created");
		}
		catch(Exception e)
		{
			System.out.println(e);
			return new InsertResult("400","Bad Request");
		}
	}
	
	@RequestMapping(value="/birds" ,method=RequestMethod.GET)
	public List<Birds> retrieveAll()
	{
		try
		{
		result.setDataSource();
		List<Birds> birds=result.selectAll();
		return birds;
		}
		catch(Exception e)
		{
			return null;
		
	}
}
	@RequestMapping(value="/birds/{id}" ,method=RequestMethod.GET)
	public Object retrieve(@PathVariable("id") String id )
	{
		try
		{
		result.setDataSource();
		Birds bird=result.select(id);
		return bird;
		}
		catch(Exception e)
		{
			return new InsertResult("404","not found");
		
	}
}
	@RequestMapping(value="DELETE/birds/{id}" ,method=RequestMethod.GET)
	public InsertResult  delete(@PathVariable("id") String id)
	{
		try
		{
		result.setDataSource();
		result.delete(id);
		return new InsertResult("201","OK");
		}
		catch(Exception e)
		{
			System.out.println(e);
			return new InsertResult("404","not found");
	}
}
}
