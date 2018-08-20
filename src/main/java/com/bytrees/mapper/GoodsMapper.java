package com.bytrees.mapper;

import org.apache.ibatis.annotations.Select;

import com.bytrees.entity.Goods;;

public interface GoodsMapper {
    @Select("select id,name from goods where id=#{goodsId} limit 1")
    public Goods get(int goodsId);

    @Select("select id,name from goods where name=#{goodsName} limit 1")
    public Goods getByName(String goodsName);
}