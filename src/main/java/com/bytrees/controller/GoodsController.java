package com.bytrees.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.dao.Goods;
import com.bytrees.utils.ResponseJson;

@RestController
public class GoodsController {
	//@Autowired
	//private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/goods/simpleList", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String getSimpleList() {
    	List<Goods> goodsList = new ArrayList<Goods>();
    	goodsList.add(new Goods(1, "测试商品"));
        goodsList.add(new Goods(2, "测试商品2"));
    	goodsList.add(new Goods(3, "蓝月亮洗衣液"));
        return JSON.toJSONString(new ResponseJson<List<Goods>>(200, "success", goodsList));
    }

    @RequestMapping(value = "/goods/list", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String getList(JdbcTemplate jdbcTemplate) {
    	try {
    		List<Map<String, Object>> list =jdbcTemplate.queryForList("select * from goods");
    		return JSON.toJSONString(new ResponseJson<List<Map<String, Object>>>(200, "", list));
    	} catch (DataAccessException ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
    }
}
