package com.bytrees.service;

import java.util.List;

import com.bytrees.dao.GoodsDao;
import com.bytrees.entity.Goods;

public class GoodsService {
	private GoodsDao goodsDao;

	public GoodsService(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

    public Goods get(int goodsId) {
    	return goodsDao.get(goodsId);
    }

    public List<Goods> getList(int offset, int limit) {
    	return goodsDao.getList(offset, limit);
    }

    public boolean create(String name) {
    	return goodsDao.create(name) == 1;
    }

    public boolean update(int goodsId, String name) {
    	return goodsDao.update(goodsId, name) == 1;
    }

    public boolean delete(int goodsId) {
    	return goodsDao.delete(goodsId) == 1;
    }
}
