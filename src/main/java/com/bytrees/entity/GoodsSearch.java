package com.bytrees.entity;

public class GoodsSearch {
    private String name;
    private int limit;
    private int lastId = 0;

    public GoodsSearch(String name, int limit) {
        this.name = name;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }
    public int getLastId() {
        return lastId;
    }
}
