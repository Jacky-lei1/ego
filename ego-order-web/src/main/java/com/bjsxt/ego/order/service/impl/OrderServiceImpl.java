package com.bjsxt.ego.order.service.impl;

import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.order.service.OrderService;
import com.bjsxt.ego.order.dao.CarItemDao;
import com.bjsxt.ego.order.entity.CarItem;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;
import com.bjsxt.ego.rpc.service.TbOrderService;
import org.apache.zookeeper.data.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lvyelanshan
 * @create 2019-10-26 15:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CarItemDao carItemDao;
    @Autowired(required = false)
    private TbOrderService tbOrderServiceProxy;

    @Override
    public Map<Long, CarItem> loadCarItemMapService(Long id) {

        Map<String, String> carMapStr = carItemDao.locadCarItemMap(String.valueOf(id));

        Map<Long,CarItem> carMap = new HashMap<>();
        for (Map.Entry<String,String>e:carMapStr.entrySet()){
            carMap.put(Long.parseLong(e.getKey()), JsonUtils.jsonToPojo(e.getValue(),CarItem.class));
        }
        return carMap;
    }

    @Override
    public Map<String, String> saveOrderService(TbOrder tbOrder, Long uid, TbOrderShipping tbOrderShipping) {

        try {
            /**
             * 封装订单表对象
             */
            Date date = new Date();
            //产生订单号(随机订单号)
            String orderid = String.valueOf(IDUtils.genItemId());
            //设置订单id
            tbOrder.setOrderId(orderid);
            //设置订单邮费
            tbOrder.setPostFee("10");
            //设置刚下的订单的状态:2表示未发货
            tbOrder.setStatus(2);
            //订单创建时间
            tbOrder.setCreateTime(date);
            //订单更新时间
            tbOrder.setUpdateTime(date);
            //订单付款世家
            tbOrder.setPaymentTime(date);
            //订单发货时间
            tbOrder.setConsignTime(date);
            //交易关闭时间
            tbOrder.setCloseTime(date);
            //设置物流
            tbOrder.setShippingName("顺丰");
            //设置物流编号
            tbOrder.setShippingCode("110110");
            //当前下单的用户ID
            tbOrder.setUserId(uid);
            //用户留言信息
            tbOrder.setBuyerMessage("请尽快发货！");
            //设置昵称
            tbOrder.setBuyerNick("9527");
            //订单是否已经评论，0表示没有评论
            tbOrder.setBuyerRate(0);

            /**
             * 封装商品明细表对象
             */
            //获得用户的购物车集合
            Map<Long,CarItem> carMap = this.loadCarItemMapService(uid);
            //创建List<TbOrderItem>,保存每次循环的订单明细对象
            List<TbOrderItem> list = new ArrayList<>();
            //购物车中有几个商品，map集合中有几个购物车对象就循环几次
            for (CarItem carItem:carMap.values()){
                //产生订单明细的主键
                String id = String.valueOf(IDUtils.genItemId());
                //创建订单明细对象
                TbOrderItem tbOrderItem = new TbOrderItem();
                //订单明细表的ID
                tbOrderItem.setId(id);
                //订单明细表的订单ID，订单号
                tbOrderItem.setOrderId(orderid);
                //循环获取购物车中的商品对象
                TbItem item = carItem.getItem();
                //设置商品描述表中的商品ID
                tbOrderItem.setItemId(String.valueOf(item.getId()));
                //设置订单商品数量（从购物车对象中获取）
                tbOrderItem.setNum(carItem.getNum());
                //设置商品标题
                tbOrderItem.setTitle(item.getTitle());
                //设置商品价格
                tbOrderItem.setPrice(item.getPrice());
                //设置商品总价
                tbOrderItem.setTotalFee(item.getPrice()*carItem.getNum());
                //设置商品图片路径
                tbOrderItem.setPicPath(item.getImages()[0]);

                list.add(tbOrderItem);
            }

            /**
             * 封装物流信息表对象
             */
            tbOrderShipping.setOrderId(orderid);
            tbOrderShipping.setReceiverPhone("15892055179");
            tbOrderShipping.setCreated(date);
            tbOrderShipping.setUpdated(date);

            //调用RPC远程服务,将封装好的对象保存到本地表中
            tbOrderServiceProxy.saveTbOrderService(tbOrder,list,tbOrderShipping);

            //页面上返回的值，将来在success页面中显示
            Map<String,String> map = new HashMap<>();
            //订单ID
            map.put("orderid",orderid);
            //订单总价(从前端的隐藏域中传过来的属性值)
            map.put("total",tbOrder.getPayment());

            /*清空购物车*/
            carItemDao.deleteCarItemMap(String.valueOf(uid));

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<TbOrder> loadOrderListService(Long id) {
        return tbOrderServiceProxy.loadTbOrderListService(id);
    }

    @Override
    public List<TbOrderItem> loadOrderItemListService(String orderid) {


        return tbOrderServiceProxy.loadTbOrderItemListService(orderid);
    }
}
