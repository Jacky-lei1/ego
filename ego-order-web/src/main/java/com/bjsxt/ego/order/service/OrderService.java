package com.bjsxt.ego.order.service;

import com.bjsxt.ego.order.entity.CarItem;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;

import java.util.List;
import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-26 15:40
 */
public interface OrderService {
    /**
     * 加载用户购物车集合
     * @param id
     * @return
     */
    public Map<Long, CarItem> loadCarItemMapService(Long id);

    /**
     * 完成用户下订单
     * uid:用来获得用户的购物车数据，通过用户ID就可以获取到购物车集合,
     */
    public Map<String,String> saveOrderService(TbOrder tbOrder, Long uid,
                                               TbOrderShipping tbOrderShipping);

    /**
     * 加载用户订单列表
     * @param id
     * @return
     */
    public List<TbOrder> loadOrderListService(Long id);

    /**
     * 查询订单明细
     * @param orderid
     * @return
     */
    public List<TbOrderItem> loadOrderItemListService(String orderid);

}
