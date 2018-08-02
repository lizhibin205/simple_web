package com.bytrees.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.bytrees.bean.Goods;
import com.bytrees.service.GoodsService;
import com.bytrees.utils.ResponseJson;

@RestController
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String get(@PathVariable(value="goodsId") int goodsId) {
		try {
			Goods goods = goodsService.get(goodsId);
			if (goods == null) {
				throw new Exception("Can't find goods(id=" + goodsId + ")");
			}
			return JSON.toJSONString(new ResponseJson<Goods>(200, "success", goods));
		} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
	}

    @RequestMapping(value = "/goods", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public String create(HttpServletRequest request) {
    	try {
    		String goodsName = request.getParameter("name");
    		if (goodsName.length() == 0) {
    			throw new Exception("Goods name can't be null");
    		}
    		if (!goodsService.create(goodsName)) {
    			throw new Exception("Create goods failure");
    		}
    		return JSON.toJSONString(new ResponseJson<Object>(200, "success", null));
    	} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
    }

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.PUT, produces={"application/json;charset=UTF-8"})
    public String update(@PathVariable(value="goodsId") int goodsId, @RequestBody MultiValueMap<String,String> requestBody) {
    	try {
    		String goodsName = requestBody.getFirst("name");
    		if (goodsName.length() == 0) {
    			throw new Exception("Goods name can't be null");
    		}
    		if (!goodsService.update(goodsId, goodsName)) {
    			throw new Exception("Update goods failure");
    		}
    		return JSON.toJSONString(new ResponseJson<Object>(200, "success", null));
    	} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
    }

    @RequestMapping(value = "/goods/{goodsId}", method = RequestMethod.DELETE, produces={"application/json;charset=UTF-8"})
    public String delete(@PathVariable(value="goodsId") int goodsId) {
    	try {
    		if (!goodsService.delete(goodsId)) {
    			throw new Exception("Delete goods failure");
    		}
    		return JSON.toJSONString(new ResponseJson<Object>(200, "success", null));
    	} catch (Exception ex) {
    		return JSON.toJSONString(new ResponseJson<Object>(500, ex.getMessage(), null));
    	}
    }
}