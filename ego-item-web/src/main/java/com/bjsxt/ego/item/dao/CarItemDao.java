package com.bjsxt.ego.item.dao;

import java.util.Map;

/**
 * 访问Redis数据库的方法
 * @author lvyelanshan
 * @create 2019-10-25 19:27
 */
public interface CarItemDao {
    /*Redis保存数据库中保存对象，要么是序列化成json字符串来保存，要么就是序列化成字节数组来保存*/
    /*这里就是序列化成json字符串来保存*/
    public void addCarMap(String uid, Map<String,String> carMap);

    /**
     * 查询某个用户对应的购物车集合
     * @param uid
     * @return
     */
    public Map<String,String> loadCarMap(String uid);

    /**
     * 获得某个用户对应的购物车中特定商品对应的购物车对象
     * @param uid
     * @param itemid
     * @return
     */
    public String loadCarItem(String uid,String itemid);

    /**
     * 修改Redis数据库中对应的购物车对象
     * @param uid
     * @param itemid
     * @param carItemStr
     */
    public void updateCarMapNum(String uid,String itemid,String carItemStr);


    /**
     * 实现商品对应的购物车删除
     * @param uid 根据UID获取到购物车集合
     * @param itemid 根据商品id获取到购物车的对象
     */
    public void deleteCarMapItem(String uid,String itemid);

    /**
     * 清空某个用户的购物车
     * @param uid
     */
    public void deleteCarMapAll(String uid);
}
