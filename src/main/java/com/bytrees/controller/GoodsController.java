package com.bytrees.controller;

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
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String get(@PathVariable(value="goodsId") int goodsId, HttpServletRequest request, HttpServletResponse response) {
        try {
            Goods goods = goodsService.get(goodsId);
            if (goods == null) {
                throw new Exception("Can't find goods(id=" + goodsId + ")");
            }
            return JSON.toJSONString(new ResponseJson<Goods>(200, "success", goods));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Goods>(500, ex.getMessage(), null));
        }
    }
}