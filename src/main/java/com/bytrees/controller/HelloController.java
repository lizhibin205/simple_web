package com.bytrees.controller;

import java.util.ArrayList;
import java.util.List;

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

        //List
        List<Message> messageList = new ArrayList<Message>();
        messageList.add(new Message(1,"1.hello"));
        messageList.add(new Message(2, "2.start"));
        messageList.add(new Message(3, "3.end"));
        modelMap.addAttribute("messageList", messageList);
        return "hello";
    }
    class Message {
    	private Integer id;
    	private String message;
    	public Message(Integer id, String message) {
    		this.id = id;
    		this.message = message;
    	}
    	public Integer getId() {
    		return id;
    	}
    	public String getMessage() {
    		return message;
    	}
    	public String toString() {
    		return message;
    	}
    }
}