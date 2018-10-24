package com.bytrees.mapper;

import org.apache.ibatis.annotations.Param;
import com.bytrees.entity.Goods;

public interface GoodsMapper {
    public Goods get(@Param("goodsId") long goodsId);
    public int create(Goods goods);
    public int modify(Goods goods);
    public int delete(@Param("goodsId") long goodsId);
}