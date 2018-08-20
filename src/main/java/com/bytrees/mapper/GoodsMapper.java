package com.bytrees.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bytrees.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    @Select("select id,name from goods where id=#{goodsId} limit 1")
    public Goods get(@Param("goodsId")int goodsId);

    @Select("select id,name from goods where id>#{lastId} and name like CONCAT('%',#{name},'%') limit #{limit}")
    public List<Goods> search(@Param("name")String name, @Param("limit")int limit, @Param("lastId")int lastId);
}