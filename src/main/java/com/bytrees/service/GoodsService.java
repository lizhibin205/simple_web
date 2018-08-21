package com.bytrees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bytrees.entity.Goods;
import com.bytrees.entity.GoodsSearch;
import com.bytrees.mapper.GoodsMapper;

public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public Goods get(int goodsId) {
        return goodsMapper.get(goodsId);
    }

    public List<Goods> search(GoodsSearch goodsSearch) {
        return goodsMapper.search(goodsSearch.getSearchNameList(), goodsSearch.getLimit(), goodsSearch.getLastId());
    }
}
