package com.bytrees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.entity.User;
import com.bytrees.service.UserService;
import com.bytrees.utils.ResponseJson;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String get(@PathVariable(value="userId") int userId) {
		User user = userService.get(userId);
		return JSON.toJSONString(new ResponseJson<User>(200, "success", user));
	}
}
