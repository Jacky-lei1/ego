package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;

import java.util.List;

/**
 *完成订单信息的保存
 * @author lvyelanshan
 * @create 2019-10-26 16:26
 */
public interface TbOrderService {
    /**
     * 完成订单信息的保存
     * 需要保存三张表
     *  1、订单表
     *  2、订单明细表
     *  3、物理配送信息表
     *
     */
    public void saveTbOrderService(TbOrder tbOrder,
                                   List<TbOrderItem> orderItems,
                                   TbOrderShipping tbOrderShipping);

    /**
     * 根据用户id，加载当前登陆用户的订单列表
     * @param id
     * @return
     */
    public List<TbOrder> loadTbOrderListService(Long id);

    /**
     * 根据订单id查询订单明细
     * @param orderid：订单id
     * @return
     */
    public List<TbOrderItem> loadTbOrderItemListService(String orderid);
}
