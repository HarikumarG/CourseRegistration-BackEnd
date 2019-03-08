package com.web.deletedao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.web.deletemodel.DeleteModel;

@Component
public class DeleteDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean delete_data(DeleteModel data)
	{
		try
		{
			List<DeleteModel> Fulldetails=new ArrayList<DeleteModel>();
			String sql="select * from user";
			Fulldetails = jdbcTemplate.query(sql,new DetailMapper());
			for(int i=0;i<Fulldetails.size();i++)
			{
				if(data.getRegno().equals(Fulldetails.get(i).getRegno()) && data.getPassword().equals(Fulldetails.get(i).getPassword()))
				{
					String sq = "delete from user where regno='"+data.getRegno()+"'";
					int change=jdbcTemplate.update(sq);
					if(change >0)
					{
						String s = "delete from registered where regno='"+data.getRegno()+"'";
						jdbcTemplate.update(s);
						return true;
					}
					else
						return false;
				}
			}
			return false;
		}
		catch (DataAccessException e) {
			throw e;
		}
	}
	private static final class DetailMapper implements RowMapper<DeleteModel>
	{
		@Override
		public DeleteModel mapRow(ResultSet resultSet,int rowNum) throws SQLException{
			DeleteModel logindetail = new DeleteModel();
			logindetail.setRegno(resultSet.getString("regno"));
			logindetail.setPassword(resultSet.getString("password"));
			return logindetail;
		}
	}



}
