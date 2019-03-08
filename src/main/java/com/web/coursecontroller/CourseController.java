package com.web.coursecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.web.coursedao.CourseDao;
import com.web.coursemodel.CourseModel;

@RestController
public class CourseController {
	@Autowired
	private CourseDao coursedata;
	
	@RequestMapping(value="/courseregister",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> coursedata(@RequestBody CourseModel data)
	{
		try
		{
			String status = coursedata.add_data(data);
			if(status!="fail")
			{	
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("SUCCESS");
				return ResponseEntity.ok(res);
		
			}
			else
			{
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("FAILURE");
				return ResponseEntity.ok(res);
		
			}

		}
		catch(DataAccessException e)
		{
			String res = new GsonBuilder().disableHtmlEscaping().create().toJson("DATA ACCESS EXCEPTION OCCURED");
			return ResponseEntity.ok(res);
		}

	}
	@RequestMapping(value="/courseunregister",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> course_data(@RequestBody CourseModel data)
	{
		try
		{
			String status = coursedata.delete_data(data);
			if(status!="fail")
			{	
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("SUCCESS");
				return ResponseEntity.ok(res);
		
			}
			else
			{
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("FAILURE");
				return ResponseEntity.ok(res);
		
			}

		}
		catch(DataAccessException e)
		{
			String res = new GsonBuilder().disableHtmlEscaping().create().toJson("DATA ACCESS EXCEPTION OCCURED");
			return ResponseEntity.ok(res);
		}

	}
	@RequestMapping(value="/coursestatus",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> course_status(@RequestBody CourseModel data)
	{
		try
		{
			List<CourseModel> status = coursedata.status_data(data);
			if(status.isEmpty())
			{	
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson("FAILURE");
				return ResponseEntity.ok(res);
			}
			else
			{
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson(status);
				return ResponseEntity.ok(res);
			}

		}
		catch(DataAccessException e)
		{
			String res = new GsonBuilder().disableHtmlEscaping().create().toJson("DATA ACCESS EXCEPTION OCCURED");
			return ResponseEntity.ok(res);
		}

	}

}


