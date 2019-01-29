package com.bytrees.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.utils.ResponseJson;

@RestController
public class HealthCheckController {
	@RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
	public ResponseEntity<ResponseJson<Object>> index() {
	    return new ResponseEntity<>(new ResponseJson<Object>(200, "sucess.", null), HttpStatus.OK);
	}
}