package com.bytrees.mapper;

import org.apache.ibatis.annotations.Select;

import com.bytrees.entity.User;

public interface UserMapper {
	@Select("select id,username from users where id=#{userId}")
    public User get(int userId);
}
