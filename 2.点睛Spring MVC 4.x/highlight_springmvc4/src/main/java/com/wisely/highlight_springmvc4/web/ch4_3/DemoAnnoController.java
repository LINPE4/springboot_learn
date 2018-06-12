package com.wisely.highlight_springmvc4.web.ch4_3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.highlight_springmvc4.domain.DemoObj;

@Controller // 1
@RequestMapping("/anno") //2
public class DemoAnnoController {

	@RequestMapping(produces = "text/plain;charset=UTF-8")	// 3 produces可定制返回的response的媒体类型和字符集
	public @ResponseBody String index(HttpServletRequest request) { // 4
		return "url:" + request.getRequestURL() + " can access";
	}

	@RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")// 5
	public @ResponseBody String demoPathVar(@PathVariable String str, //3
			HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access,str: " + str;
	}

	//http://localhost:8080/highlight_springmvc4/anno/requestParam?id=1000&id=10
	@RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8") //6 演示常规的request参数获取
	public @ResponseBody String passRequestParam(Long id, @RequestParam("id") Long ids,
			HttpServletRequest request) {
		
		return "url:" + request.getRequestURL() + " can access,id: " + id + " or " + ids;

	}

	//http://localhost:8080/highlight_springmvc4/anno/obj?id=1&name=peter
	@RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")//7 演示解释参数到对象
	@ResponseBody //8
	public String passObj(DemoObj obj, HttpServletRequest request) {
		
		 return "url:" + request.getRequestURL() 
		 			+ " can access, obj id: " + obj.getId()+" obj name:" + obj.getName();

	}

	@RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8")//9
	public @ResponseBody String remove(HttpServletRequest request) {
		
		return "url:" + request.getRequestURL() + " can access";
	}

}
