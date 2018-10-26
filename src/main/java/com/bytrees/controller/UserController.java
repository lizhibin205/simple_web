package com.bytrees.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.entity.User;
import com.bytrees.service.UserService;
import com.bytrees.utils.ResponseJson;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
public class UserController {
    private final static String LOGIN_SESSION_NAME = "user_status"; 
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String status(HttpServletRequest request, HttpServletResponse response, HttpSession session) {		
		try {
			String loginSession = session.getAttribute(LOGIN_SESSION_NAME).toString();
			String[] loginSessionInfo = loginSession.split("\\|");
			int userId = Integer.parseInt(loginSessionInfo[0]);
			String username = loginSessionInfo[1];
			User user = userService.get(userId);
			if (user == null) {
				throw new Exception("User not exists");
			}
			if (!username.equals(user.getUsername())) {
				throw new Exception("User vaild fail");
			}
			return JSON.toJSONString(new ResponseJson<User>(200, "success", null));
		} catch (Exception ex) {
			return JSON.toJSONString(new ResponseJson<User>(200, ex.getMessage(), null));
		}
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET}, produces={"application/json;charset=UTF-8"})
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username == null || username.length() < 6) {
				throw new Exception("username check fail");
			}
			if (password == null || password.length() < 6) {
				throw new Exception("password check fail");
			}
			User user = userService.getByUsername(username);
			if (user != null && password.equals(user.getPassword())) {
				session.setAttribute(LOGIN_SESSION_NAME, user.getId().toString() + "|" + user.getUsername());
			} else {
				throw new Exception("login fail");
			}
			return JSON.toJSONString(new ResponseJson<User>(200, "success.", null));
		} catch (Exception ex) {
			return JSON.toJSONString(new ResponseJson<User>(500, ex.getMessage(), null));
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String register(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username == null || username.length() < 6) {
				throw new Exception("username check fail");
			}
			if (password == null || password.length() < 6) {
				throw new Exception("password check fail");
			}
			User user = userService.getByUsername(username);
			if (user != null) {
				throw new Exception("User exists");
			}
			if (userService.create(new User(username, password)) == 0) {
				throw new Exception("User create fail");
			}
			return JSON.toJSONString(new ResponseJson<User>(200, "success.", user));
		} catch (Exception ex) {
			return JSON.toJSONString(new ResponseJson<User>(500, ex.getMessage(), null));
		}
	}
}
