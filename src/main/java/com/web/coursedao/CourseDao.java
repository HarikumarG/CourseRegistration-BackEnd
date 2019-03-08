package com.web.coursedao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.web.coursemodel.CourseModel;

@Component
public class CourseDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String add_data(CourseModel data)
	{
		try
		{
			String sql = "insert into registered values('"+data.getRegno()+"','"+data.getCourseid()+"')";
			int change=jdbcTemplate.update(sql);
			if(change >0)
				return "success";
			else
				return "fail";
		}
		catch(DataAccessException e)
		{
			throw e;
		}
	}
	
	public String delete_data(CourseModel data)
	{
		try
		{
			String sql = "delete from registered where regno='"+data.getRegno()+"' and courseid='"+data.getCourseid()+"'";
			int change=jdbcTemplate.update(sql);
			if(change >0)
				return "success";
			else
				return "fail";
		}
		catch(DataAccessException e)
		{
			throw e;
		}
	}
	public List<CourseModel> status_data(CourseModel data)
	{
		List<CourseModel> Fulldetail=new ArrayList<CourseModel>();
		List<CourseModel> Fulldetails=new ArrayList<CourseModel>();
		String sql="select * from registered";
	    Fulldetails = jdbcTemplate.query(sql,new DetailMapper());
	    int flag=0;
	    for(int i=0;i<Fulldetails.size();i++)
		{
			if(data.getRegno().equals(Fulldetails.get(i).getRegno()))
			{
				Fulldetail.add(Fulldetails.get(i));
				flag=1;
				
			}
		}
	    if(flag==1)
	    {
	    	return Fulldetail;
	    }
	    else
	    {
	    	return null;
	    }
	}
	private static final class DetailMapper implements RowMapper<CourseModel>
	{
		@Override
		public CourseModel mapRow(ResultSet resultSet,int rowNum) throws SQLException{
			CourseModel statusdetail = new CourseModel();
			statusdetail.setRegno(resultSet.getString("regno"));
			statusdetail.setCourseid(resultSet.getString("courseid"));
			return statusdetail;
		}
	}


}
