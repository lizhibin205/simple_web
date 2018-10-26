package com.bytrees.mapper;

import org.apache.ibatis.annotations.Param;

import com.bytrees.entity.User;

public interface UserMapper {
    public User get(@Param("userId") long userId);
	public User getByUsername(@Param("username") String username);
	public int create(User user);
}