package com.bytrees.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.entity.User;
import com.bytrees.service.UserService;
import com.bytrees.utils.Helper;
import com.bytrees.utils.ResponseJson;
import com.bytrees.utils.UserLogin;
import com.bytrees.vo.UserVO;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ResponseJson<String>> status(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String loginUsername = UserLogin.getLoginUserName(session);
		if (loginUsername == null) {
			return new ResponseEntity<>(new ResponseJson<String>(200, "success.", null), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseJson<String>(200, "success.", loginUsername), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<ResponseJson<Object>> login(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws NoSuchAlgorithmException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			return new ResponseEntity<>(new ResponseJson<Object>(500, "login failure.", null), HttpStatus.OK);
		}
		password = Helper.md5(password);
		User user = userService.getByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(new ResponseJson<Object>(500, "login failure.", null), HttpStatus.OK);
		}
		if (!password.equals(user.getPassword())) {
			return new ResponseEntity<>(new ResponseJson<Object>(500, "login failure.", null), HttpStatus.OK);
		}
		UserLogin.setLoginSession(session, user);
		return new ResponseEntity<>(new ResponseJson<Object>(200, "success.", null), HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<ResponseJson<Object>> register(@RequestBody UserVO userVO) throws NoSuchAlgorithmException {
		if (userVO.getUsername() == null || userVO.getPassword() == null) {
			return new ResponseEntity<>(new ResponseJson<Object>(500, "register failure.", null), HttpStatus.OK);
		}
		if (userService.getByUsername(userVO.getUsername()) != null) {
			return new ResponseEntity<>(new ResponseJson<Object>(500, "register failure.", null), HttpStatus.OK);
		}
		userVO.setPassword(Helper.md5(userVO.getPassword()));
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		int result = userService.create(user);
    	if (result == 1) {
    		return new ResponseEntity<>(new ResponseJson<Object>(200, "success.", null), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(new ResponseJson<Object>(500, "register failure.", null), HttpStatus.OK);
    	}
	}
}
