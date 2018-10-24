package com.bytrees.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.bytrees.entity.Goods;
import com.bytrees.mapper.GoodsMapper;

public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public Goods get(long goodsId) {
        return goodsMapper.get(goodsId);
    }

    public int create(Goods goods) {
    	return goodsMapper.create(goods);
    }
    
    public int modify(Goods goods) {
    	return goodsMapper.modify(goods);
    }

    public int delete(long goodsId) {
    	return goodsMapper.delete(goodsId);
    }
}
