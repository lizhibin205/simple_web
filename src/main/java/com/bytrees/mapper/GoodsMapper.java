package com.bytrees.mapper;

import org.apache.ibatis.annotations.Param;
import com.bytrees.entity.Goods;

public interface GoodsMapper {
    public Goods get(@Param("goodsId")int goodsId);
    public int create(Goods goods);
}