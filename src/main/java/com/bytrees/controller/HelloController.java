package com.bytrees.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(ModelMap modelMap) {
    	//String
    	String message = "hello, spring web!";
        modelMap.addAttribute("message", message);
        return "hello";
    }

    @RequestMapping(value = "/hello/redirect", method = RequestMethod.GET)
    public String redirect(HttpServletResponse response) {
    	try {
    		response.sendRedirect("/hello");
    	} catch (Exception ex) {
    		
    	}
    	return null;
    }
}