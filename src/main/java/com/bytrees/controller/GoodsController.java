package com.bytrees.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.dao.Goods;
import com.bytrees.utils.ResponseJson;

@RestController
public class GoodsController {
    @RequestMapping(value = "/goods/list", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String getList() {
    	List<Goods> goodsList = new ArrayList<Goods>();
    	goodsList.add(new Goods(1, "测试商品"));
        goodsList.add(new Goods(2, "测试商品2"));
    	goodsList.add(new Goods(3, "蓝月亮洗衣液"));
        return JSON.toJSONString(new ResponseJson<List<Goods>>(200, "success", goodsList));
    }
}
