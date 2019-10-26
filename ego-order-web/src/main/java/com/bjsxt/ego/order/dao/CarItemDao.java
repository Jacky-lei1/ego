package com.bjsxt.ego.order.dao;

import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-26 15:31
 */
public interface CarItemDao {
    /**
     * 加载用户的购物车集合
     */
    public Map<String,String> locadCarItemMap(String uid);

    /**
     * 下完订单，需要将购物车清空
     */
    public void deleteCarItemMap(String uid);
}
