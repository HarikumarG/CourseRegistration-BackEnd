package com.web.registerdao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.web.registermodel.RegisterModel;

@Component
public class RegisterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean add_data(RegisterModel data)
	{
		try
		{
			String sql = "insert into user values('"+data.getRegno()+"','"+data.getName().toUpperCase()+"','"+data.getInitial().toUpperCase()+"','"+data.getDept().toUpperCase()+"','"+data.getYear()+"','"+data.getSection()+"','"+data.getEmail()+"','"+data.getPassword()+"','"+data.getMobileno()+"','"+data.getGender().toUpperCase()+"','0')";
			int change=jdbcTemplate.update(sql);
			if(change >0)
				return true;
			else
				return false;
		}
		catch(DataAccessException e)
		{
			throw e;
		}
	}

}
