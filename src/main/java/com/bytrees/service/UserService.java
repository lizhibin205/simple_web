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
}
