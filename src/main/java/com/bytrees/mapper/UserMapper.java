package com.bytrees.mapper;

import org.apache.ibatis.annotations.Select;

import com.bytrees.entity.User;

public interface UserMapper {
	@Select("select id,username,password from users where id=#{userId}")
    public User get(int userId);

	@Select("select id,username,password from users where username=#{username}")
	public User getByUsername(String username);
}