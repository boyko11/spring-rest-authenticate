package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.Donkey;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/donkey")
public class JsonDonkeyController {

  private static Map<String, Donkey> donkeyRecords = new HashMap<String, Donkey>();
  static{
    donkeyRecords.put("1", new Donkey("1", "SuperDonkey", "angry", 5));
    donkeyRecords.put("2", new Donkey("2", "SmartDonkey", "mellow", 7));
    donkeyRecords.put("3", new Donkey("3", "FunDonkey", "fun", 3));
    donkeyRecords.put("4", new Donkey("4", "AngryDonkey", "angry", 3));
    donkeyRecords.put("5", new Donkey("5", "LazyDonkey", "lazy", 4));
  }

  @RequestMapping(value="{id}", method = RequestMethod.GET, produces={"application/json"})
  public @ResponseBody Donkey getPerson(@PathVariable String id){
    Donkey donkey = donkeyRecords.get(id);
    if(donkey == null) {
    	return new Donkey();
    }
    return donkey;
  }
  
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody Donkey create(@RequestBody Donkey donkey) {

		donkeyRecords.put(donkey.getId(), donkey);

		return donkey;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Donkey update(@RequestBody Donkey donkey) {

		Donkey donkeyToUpdate = donkeyRecords.get(donkey.getId());
		donkeyToUpdate.setAge(donkey.getAge());
		donkeyToUpdate.setName(donkey.getName());
		donkeyToUpdate.setTemper(donkey.getTemper());
		
		return donkeyRecords.get(donkeyToUpdate.getId());
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Donkey delete(@PathVariable final String id) {

		Donkey personToDelete = donkeyRecords.remove(id);
		return personToDelete;
	}
  
}
