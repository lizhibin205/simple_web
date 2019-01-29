package com.bytrees.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
	@ExceptionHandler(Exception.class)
    public ModelAndView index(Exception ex) {
		ModelAndView view = new ModelAndView("error");
		view.addObject("exceptionMessage", ex.getMessage());
		return view;
    }
}
