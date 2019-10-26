package com.bjsxt.ego.item.service;

import com.bjsxt.ego.item.entity.CarItem;

import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-25 19:34
 */
public interface CarItemService {
    /**
     * 通过商品id获得商品对象，在通过用户UID将商品保存到数据库中
     * @param itemid
     * @param uid
     */
    public void addItemToCarService(Long itemid,Long uid);

    /**
     * 加载用户购物车列表
     * @param uid
     * @return
     */
    public Map<Long, CarItem> loadCarItemListService(Long uid);

    /**
     *  修改购物车数量，就是修改购物车对象中的num属性
     * @param itemid：通过商品id能获取到这个集合中某个购物车的对象
     * @param uid :通过UID能获取到购物车集合
     * @return
     */
    public String updateCarItemNumService(Long itemid,Long uid,Integer numuber);

    /**
     * 删除用户的某个商品对应的购物车
     * @param itemid
     * @param uid
     */
    public void deleteCarItemService(Long itemid,Long uid);


    /**
     * 清空用户购物车
     * @param uid
     */
    public void deleteCarItemAllService(Long uid);
}
