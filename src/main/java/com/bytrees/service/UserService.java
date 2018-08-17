package com.bytrees.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytrees.entity.User;
import com.bytrees.mapper.UserMapper;

public class UserService {
	@Autowired
    private UserMapper userMapper;

	public User get(int userId) {
		return userMapper.get(userId);
	}

	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	public String userLogin(String username, String password) {
		User user = getByUsername(username);
		if (user == null) {
			return null;
		}
		return "";
	}
}
