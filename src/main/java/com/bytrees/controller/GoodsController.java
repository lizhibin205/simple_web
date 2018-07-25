package com.bytrees.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.utils.ResponseJson;

@RestController
public class GoodsController {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/goods/{goodId}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String get(@PathVariable(value="goodId") int goodId) {
		try {
			Map<String, Object> goods = jdbcTemplate.queryForMap("select * from goods where id=?", goodId);
			return JSON.toJSONString(new ResponseJson<Map<String, Object>>(200, "success", goods));
		} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
	}

    @RequestMapping(value = "/goods/list", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String getList(HttpServletRequest request) {
    	try {
    		int pageSize = 50;
    		int page = Integer.parseInt(request.getParameter("page"));
    		if (page < 1) {
    			page = 1;
    		}
    		int offset = (page - 1) * pageSize;
    		List<Map<String, Object>> list =jdbcTemplate.queryForList("select * from goods limit ?,?", offset, pageSize);
    		return JSON.toJSONString(new ResponseJson<List<Map<String, Object>>>(200, "success", list));
    	} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
    }

    @RequestMapping(value = "/goods/create", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public String create(HttpServletRequest request) {
    	try {
    		String goodsName = request.getParameter("name");
    		return goodsName;
    	} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
    }
}
