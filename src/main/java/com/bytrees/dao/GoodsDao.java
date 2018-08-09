package com.bytrees.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bytrees.entity.Goods;

public class GoodsDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    public Goods get(int goodsId) {
    	try {
    		return jdbcTemplate.queryForObject("select `id`,`name` from goods where id=? and is_deleted=0 limit 1", 
    			new Object[] {goodsId}, 
    			new BeanPropertyRowMapper<Goods>(Goods.class));
    	} catch (DataAccessException ex) {
    		return null;
    	}
    }

    public List<Goods> getList(int offset, int limit) {
    	try {
    		return jdbcTemplate.query("select `id`,`name` from goods where is_deleted=0 limit ?,?", 
    			new Object[] {offset, limit}, 
    			new BeanPropertyRowMapper<Goods>(Goods.class));
    	} catch (DataAccessException ex) {
    		return new ArrayList<Goods>();
    	}
    }

    public int create(String name) {
    	try {
    		return jdbcTemplate.update("insert into `goods`(`name`) values(?)", name);
    	} catch (DataAccessException ex) {
    		return 0;
    	}
    }

    public int update(int goodsId, String name) {
    	try {
    		return jdbcTemplate.update("update `goods` set `name`=? where id=? limit 1", name, goodsId);
    	} catch (DataAccessException ex) {
    		return 0;
    	}
    }

    public int delete(int goodsId) {
    	try {
    		return jdbcTemplate.update("update `goods` set `is_deleted`=1 where id=? limit 1", goodsId);
    	} catch (DataAccessException ex) {
    		return 0;
    	}
    }
}
