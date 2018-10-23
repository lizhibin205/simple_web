package com.bytrees.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.entity.Goods;
import com.bytrees.service.GoodsService;
import com.bytrees.utils.ResponseJson;

@RestController
@RequestMapping(value = "/v1/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/{goodsId}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String get(@PathVariable(value="goodsId") int goodsId, HttpServletRequest request, HttpServletResponse response) {
    	try {
    		Goods goods = goodsService.get(goodsId);
            if (goods == null) {
                throw new Exception("Can't find goods(id=" + goodsId + ")");
            }
            return JSON.toJSONString(new ResponseJson<Goods>(200, "success", goods));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Goods>(404, ex.getMessage(), null));
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public String create(HttpServletRequest request, HttpServletResponse response) {
    	try {
    		Goods goods = new Goods();
    		goods.setName(request.getParameter("name"));
    		goods.setCreateTime(new Timestamp(new Date().getTime()));
    		if (goodsService.create(goods) == 0) {
    			throw new Exception("Create goods failure");
    		}
            return JSON.toJSONString(new ResponseJson<Goods>(200, "success", null));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Goods>(500, ex.getMessage(), null));
        }
    }
}