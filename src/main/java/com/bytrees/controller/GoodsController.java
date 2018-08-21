package com.bytrees.controller;

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
import com.bytrees.entity.GoodsSearch;
import com.bytrees.service.GoodsService;
import com.bytrees.utils.ResponseJson;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

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

    @RequestMapping(value = "/goods/search", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String search(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            if (name.isEmpty()) {
                throw new Exception("Search name can't be empty.");
            }
            GoodsSearch goodsSearch = new GoodsSearch();
            int lastId = Integer.parseInt(request.getParameter("last_id"));
            if (lastId > 0) {
                goodsSearch.setLastId(lastId);
            }
            List<Term> words = SpeedTokenizer.segment(name);
            for (Term term : words) {
                goodsSearch.addName(term.word);
            }
            List<Goods> goodsList = goodsService.search(goodsSearch);
            return JSON.toJSONString(new ResponseJson<List<Goods>>(200, "success", goodsList));
        } catch (Exception ex) {
            return JSON.toJSONString(new ResponseJson<Goods>(500, ex.getMessage(), null));
        }
    }

    @RequestMapping(value = "/goods/hanlp", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String hanlp(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        List<Term> words = SpeedTokenizer.segment(name);
        return words.get(0).word;
    }
}