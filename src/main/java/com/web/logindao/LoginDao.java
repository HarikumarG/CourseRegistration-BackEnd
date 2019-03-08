package com.web.logindao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.web.loginmodel.LoginModel;

@Component
public class LoginDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String check_data(LoginModel data)
	{
		List<LoginModel> Fulldetails=new ArrayList<LoginModel>();
		String sql="select * from user";
	    Fulldetails = jdbcTemplate.query(sql,new DetailMapper());
		for(int i=0;i<Fulldetails.size();i++)
		{
			if(data.getEmail().equals(Fulldetails.get(i).getEmail()) && data.getPassword().equals(Fulldetails.get(i).getPassword()))
			{
				return Fulldetails.get(i).getRegno();
			}
		}
		return "fail";
	}
	private static final class DetailMapper implements RowMapper<LoginModel>
	{
		@Override
		public LoginModel mapRow(ResultSet resultSet,int rowNum) throws SQLException{
			LoginModel logindetail = new LoginModel();
			logindetail.setEmail(resultSet.getString("email"));
			logindetail.setPassword(resultSet.getString("password"));
			logindetail.setRegno(resultSet.getString("regno"));
			return logindetail;
		}
	}

}
