package com.bytrees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bytrees.entity.User;
import com.bytrees.mapper.UserMapper;

@Component
public class UserService {
	@Autowired
    private UserMapper userMapper;

	public User get(int userId) {
		return userMapper.get(userId);
	}

	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	public int create(User user) {
		return userMapper.create(user);
	}
}
