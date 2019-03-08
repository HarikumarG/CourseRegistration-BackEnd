package com.web.editcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.web.editdao.EditDao;
import com.web.editmodel.EditModel;

@RestController
public class EditController {

	@Autowired
	private EditDao editdata;
	@RequestMapping(value="/profiledata",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateform(@RequestBody EditModel data) 
	{
		try
		{
			ArrayList<EditModel> status = editdata.get_data(data);
			if(status != null)
			{	
				String res = new GsonBuilder().disableHtmlEscaping().create().toJson(status);
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
	@RequestMapping(value="/edituser",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updatedata(@RequestBody EditModel data) 
	{
		try
		{
			Boolean status = editdata.update_data(data);
			if(status)
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

}
