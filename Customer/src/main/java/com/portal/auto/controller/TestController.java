package com.portal.auto.controller;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/home" , method=RequestMethod.GET, produces ="application/json")
	public @ResponseBody String check(Map<String, Object> model) {
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("name", "Hello");
		arr.add(obj);
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "Admin");
		arr.add(obj1);
		return arr.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/exec" , method=RequestMethod.GET, produces ="application/json")
	public @ResponseBody String getnext(Map<String, Object> model) {
		JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("name", "hello");
		arr.add(obj);
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "exec");
		arr.add(obj1);
		return arr.toJSONString();
	}
}
