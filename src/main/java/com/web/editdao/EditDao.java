package com.web.editdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.web.editmodel.EditModel;

@Component
public class EditDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Boolean update_data(EditModel data)
	{
		try
		{
			String sql ="update user set name='"+data.getName().toUpperCase()+"',initial='"+data.getInitial().toUpperCase()+"',dept='"+data.getDept().toUpperCase()+"',year='"+data.getYear()+"',section='"+data.getSection()+"',email='"+data.getEmail()+"',password='"+data.getPassword()+"',mobileno='"+data.getMobileno()+"',gender='"+data.getGender().toUpperCase()+"',forgotpassword='0' where regno='"+data.getRegno()+"'";
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

	public ArrayList<EditModel> get_data(EditModel data)
	{
		try
		{
			ArrayList<EditModel> Fulldetail=new ArrayList<EditModel>();
			List<EditModel> Fulldetails=new ArrayList<EditModel>();
			String sql="select * from user";
			Fulldetails = jdbcTemplate.query(sql,new DetailMapper());
			for(int i=0;i<Fulldetails.size();i++)
			{
				if(data.getRegno().equals(Fulldetails.get(i).getRegno()))
				{
					Fulldetail.add(Fulldetails.get(i));
					return Fulldetail;
				}
			}
			return null;
		}
		catch(DataAccessException e)
		{
			throw e;
		}
	}
	private static final class DetailMapper implements RowMapper<EditModel>
	{
		@Override
		public EditModel mapRow(ResultSet resultSet,int rowNum) throws SQLException{
			EditModel detail = new EditModel();
			detail.setRegno(resultSet.getString("regno"));
			detail.setName(resultSet.getString("name"));
			detail.setInitial(resultSet.getString("initial"));
			detail.setDept(resultSet.getString("dept"));
			detail.setYear(resultSet.getString("year"));
			detail.setSection(resultSet.getString("section"));
			detail.setEmail(resultSet.getString("email"));
			detail.setPassword(resultSet.getString("password"));
			detail.setMobileno(resultSet.getString("mobileno"));
			detail.setGender(resultSet.getString("gender"));
			return detail;
		}
	}

}
