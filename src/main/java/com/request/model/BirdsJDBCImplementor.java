package com.request.model;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class BirdsJDBCImplementor {
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate con;
	@Value("${application.dbURL}")
	private String DB_URL;
	@Value("${application.userName}")
	private String user;
	@Value("${application.password}")
	private String password;

    public void setDataSource()throws Exception {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        con = new JdbcTemplate(dataSource);
    }
    
	public void insert(Birds req[])throws Exception
    {
    	
    	for(Birds obj:req)
    	{
    		String continents=String.join(",", obj.getContinents());
    		String adddedOn=obj.getAddedOn();
    		 Object date=null;
			if(obj.getAddedOn()!=null)
			date = new SimpleDateFormat("yyyy-MM-dd").parse(adddedOn);
			else
			{
			date =new Date();
			date=new SimpleDateFormat("yyyy-MM-dd").format((Date)date);
			}
			if(obj.getVisible()==null)
			obj.setVisible(false);
    		 con.update("INSERT INTO birds (id,name,family,continents,visible,addedOn) VALUES(?,?,?,?,?,?)",
                     new Object[] { obj.getId(), obj.getName(),obj.getFamily(),continents,obj.getVisible(),date});
    	}
    }
	public List<Birds>  selectAll()throws Exception
	{
		
		List<Birds> result=con.query("select * from birds where visible=true",new RowMapper<Birds>() {
			
			@Override
			public Birds mapRow(ResultSet rs, int rowNum) throws SQLException {
				 Birds birds=new Birds();
		            birds.setId(rs.getString(1));
		            birds.setName(rs.getString(2));
		            birds.setFamily(rs.getString(3));
		            birds.setVisible(rs.getBoolean(4));
		            birds.setContinents(rs.getString(5).split("[,]"));
		            birds.setAddedOn(rs.getString(6));
		            return birds;
			}

		});
		if(result==null)
			throw new Exception();
		else
		return result;
	}
	public Birds select(String id)throws Exception
	{
		Birds result=con.queryForObject("select * from birds where visible=false and id=?",new Object[]{id},new RowMapper<Birds>() {
			@Override
			public Birds mapRow(ResultSet rs, int rowNum) throws SQLException {
				 Birds birds=new Birds();
		            birds.setId(rs.getString(1));
		            birds.setName(rs.getString(2));
		            birds.setFamily(rs.getString(3));
		            birds.setVisible(rs.getBoolean(4));
		            birds.setContinents(rs.getString(5).split("[,]"));
		            birds.setAddedOn(rs.getString(6));
		            return birds;
			}
			});
		if(result==null)
			throw new Exception();
		else
		return result;
	}
	public void delete(String id)throws Exception
	{
		int rows=con.update("delete from birds where id=?",id);
		if(rows==0)
			throw new Exception();
	}
}
