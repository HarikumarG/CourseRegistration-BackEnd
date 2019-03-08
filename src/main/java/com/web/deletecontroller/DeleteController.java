package com.web.deletecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;
import com.web.deletedao.DeleteDao;
import com.web.deletemodel.DeleteModel;

@RestController
public class DeleteController {
	
	@Autowired
	private DeleteDao deletedata;
	@RequestMapping(value="/deleteuser",method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> delete(@RequestBody DeleteModel data) 
	{
		try
		{
			boolean status = deletedata.delete_data(data);
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
