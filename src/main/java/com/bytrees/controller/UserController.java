package com.bytrees.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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

	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String userLogin(HttpServletRequest request) {
		try {
			//find user exists
			User user = userService.getByUsername(request.getParameter("username"));
			if (user == null) {
				throw new Exception("login failed.");
			}
			String password = DigestUtils.md5DigestAsHex(request.getParameter("password").getBytes());
			if (password.compareTo(user.getPassword()) != 0) {
				throw new Exception("login failed.");
			}
			//set login session
			
			//output
			return JSON.toJSONString(new ResponseJson<User>(200, "success.", null));
		} catch (Exception ex) {
			return JSON.toJSONString(new ResponseJson<User>(500, ex.getMessage(), null));
		}
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String get(@PathVariable(value="userId") int userId) {
		try {
			User user = userService.get(userId);
			if (user == null) {
				throw new Exception("Can't find user(id=" + userId + ")");
			}
			return JSON.toJSONString(new ResponseJson<User>(200, "success.", user));
		} catch (Exception ex) {
			return JSON.toJSONString(new ResponseJson<User>(200, ex.getMessage(), null));
		}
	}
}
