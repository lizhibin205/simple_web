package com.bytrees.controller;

import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String get(@PathVariable(value="goodsId") long  goodsId, HttpServletRequest request, HttpServletResponse response) {
    	try {
    		if (goodsId < 1) {
    			throw new Exception("Goods id must greater than 0");
    		}
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
    		String goodsName = request.getParameter("name");
    		if (goodsName == null || goodsName.length() == 0) {
    			throw new Exception("Goods name can't be empty");
    		}
    		Goods goods = new Goods();	
    		goods.setName(goodsName);
    		goods.setCreateTime(new Timestamp(new Date().getTime()));
    		if (goodsService.create(goods) == 0) {
    			throw new Exception("Create goods failure");
    		}
            return JSON.toJSONString(new ResponseJson<Goods>(200, "success", null));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Goods>(500, ex.getMessage(), null));
        }
    }

    @RequestMapping(value = "/{goodsId}", method = RequestMethod.PUT, produces={"application/json;charset=UTF-8"})
    public String modify(@PathVariable(value="goodsId") long goodsId, @RequestBody MultiValueMap<String,String> requestBody) {
    	try {
    		if (goodsId < 1) {
    			throw new Exception("Goods id must greater than 0");
    		}
    		String goodsName = requestBody.getFirst("name");
    		if (goodsName == null || goodsName.length() == 0) {
    			throw new Exception("Goods name can't be empty");
    		}
    		Goods goods = new Goods();
    		goods.setId(goodsId);
    		goods.setName(goodsName);
    		if (goodsService.modify(goods) == 0) {
    			throw new Exception("Modify goods failure");
    		}
            return JSON.toJSONString(new ResponseJson<Goods>(200, "success", null));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Exception>(500, ex.getMessage(), null));
        }
    }

    @RequestMapping(value = "/{goodsId}", method = RequestMethod.DELETE, produces={"application/json;charset=UTF-8"})
    public String delete(@PathVariable(value="goodsId") long goodsId) {
    	try {
    		if (goodsId < 1) {
    			throw new Exception("Goods id must greater than 0");
    		}
    		if (goodsService.delete(goodsId) == 0) {
    			throw new Exception("Delete goods failure");
    		}
            return JSON.toJSONString(new ResponseJson<Goods>(200, "success", null));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Exception>(500, ex.getMessage(), null));
        }
    }
}