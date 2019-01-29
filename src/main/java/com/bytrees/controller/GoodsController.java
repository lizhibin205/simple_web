package com.bytrees.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.entity.Goods;
import com.bytrees.service.GoodsService;
import com.bytrees.utils.ResponseJson;
import com.bytrees.vo.GoodsVO;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @RequestMapping(value = "/{goodsId}", method = RequestMethod.GET)
    public ResponseEntity<ResponseJson<GoodsVO>> get(@PathVariable(value="goodsId") long goodsId, HttpServletRequest request, HttpServletResponse response) {
    	Goods goods = goodsService.get(goodsId);
    	if (goods == null) {
    		return new ResponseEntity<>(new ResponseJson<GoodsVO>(404, "goods not exists.", null), HttpStatus.OK);
    	}
    	GoodsVO goodsVO = new GoodsVO();
    	BeanUtils.copyProperties(goods, goodsVO);
        return new ResponseEntity<>(new ResponseJson<GoodsVO>(200, "success.", goodsVO), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseJson<Object>> create(@RequestBody GoodsVO goodsVO) {
    	Goods goods = new Goods();
    	BeanUtils.copyProperties(goodsVO, goods);
    	int result = goodsService.create(goods);
    	if (result == 1) {
    		return new ResponseEntity<>(new ResponseJson<Object>(200, "success.", null), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(new ResponseJson<Object>(500, "create failure.", null), HttpStatus.OK);
    	}
    }

    @RequestMapping(value = "/{goodsId}/edit", method = RequestMethod.POST)
    public ResponseEntity<ResponseJson<Object>> modify(@PathVariable(value="goodsId") long goodsId, @RequestBody GoodsVO goodsVO) {
    	Goods goods = new Goods();
    	BeanUtils.copyProperties(goodsVO, goods);
    	int result = goodsService.modify(goods);
    	if (result == 1) {
    		return new ResponseEntity<>(new ResponseJson<Object>(200, "success.", null), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(new ResponseJson<Object>(500, "edit failure.", null), HttpStatus.OK);
    	}
    }

    @RequestMapping(value = "/{goodsId}/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseJson<Object>> delete(@PathVariable(value="goodsId") long goodsId) {
    	int result = goodsService.delete(goodsId);
    	if (result == 1) {
    		return new ResponseEntity<>(new ResponseJson<Object>(200, "success.", null), HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>(new ResponseJson<Object>(500, "delete failure.", null), HttpStatus.OK);
    	}
    }
}