package com.web.forgotpassworddao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.web.forgotpasswordmodel.ForgotpasswordModel;


@Component
public class ForgotpasswordDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public boolean add_data(ForgotpasswordModel data)
	{
		try
		{
			String sql="update user set password='"+data.getPassword()+"',forgotpassword='0' where email='"+data.getEmail()+"'";
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
	public boolean add_code(String code,ForgotpasswordModel data)
	{
		try
		{
			String sql = "update user set forgotpassword='"+code+"' where email='"+data.getEmail()+"'";
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
	
	public String check_data(ForgotpasswordModel data)
	{
		List<ForgotpasswordModel> Fulldetails=new ArrayList<ForgotpasswordModel>();
		String sql="select * from user";
	    Fulldetails = jdbcTemplate.query(sql,new DetailMapper());
		for(int i=0;i<Fulldetails.size();i++)
		{
			if(data.getEmail().equals(Fulldetails.get(i).getEmail()))
			{
				return "success";
			}
		}
		return "fail";
	}
	public String check_code_data(ForgotpasswordModel data)
	{
		List<ForgotpasswordModel> Fulldetails=new ArrayList<ForgotpasswordModel>();
		String sql="select * from user";
	    Fulldetails = jdbcTemplate.query(sql,new DetailMapper());
		for(int i=0;i<Fulldetails.size();i++)
		{
			if(data.getEmail().equals(Fulldetails.get(i).getEmail()) && data.getCode().equals(Fulldetails.get(i).getCode()))
			{
				return "success";
			}
		}
		return "fail";
	}
	private static final class DetailMapper implements RowMapper<ForgotpasswordModel>
	{
		@Override
		public ForgotpasswordModel mapRow(ResultSet resultSet,int rowNum) throws SQLException{
			ForgotpasswordModel detail = new ForgotpasswordModel();
			detail.setEmail(resultSet.getString("email"));
			detail.setCode(resultSet.getString("forgotpassword"));
			return detail;
		}
	}

}
