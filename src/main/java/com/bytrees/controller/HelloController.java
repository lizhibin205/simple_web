package com.bytrees.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello() {
    	ModelAndView view = new ModelAndView("hello");
    	view.addObject("message", "hello, spring web!");
        return view;
    }

    @RequestMapping(value = "/hello/redirect", method = RequestMethod.GET)
    public void redirect(HttpServletResponse response) throws IOException,IllegalStateException  {
    	response.sendRedirect("/hello");
    }
}