package com.bytrees;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {
    @RequestMapping(value = "/goods/list", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String getList() {
        return "[{\"id\": 1, \"name\": \"测试商品\"}, {\"id\": 2, \"name\": \"测试商品2\"}, {\"id\": 3, \"name\": \"蓝月亮洗衣液\"}]";
    }
}
