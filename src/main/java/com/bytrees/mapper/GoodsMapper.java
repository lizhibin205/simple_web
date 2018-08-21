package com.bytrees.mapper;

import org.apache.ibatis.annotations.Param;

import com.bytrees.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    public Goods get(@Param("goodsId")int goodsId);

    public List<Goods> search(@Param("searchNameList")List<String> searchNameList, @Param("limit")int limit, @Param("lastId")int lastId);
}