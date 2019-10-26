package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.rpc.mapper.TbOrderItemMapper;
import com.bjsxt.ego.rpc.mapper.TbOrderMapper;
import com.bjsxt.ego.rpc.mapper.TbOrderShippingMapper;
import com.bjsxt.ego.rpc.pojo.*;
import com.bjsxt.ego.rpc.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-26 16:31
 */
@Service
public class TbOrderServiceImpl implements TbOrderService{

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Override
    public void saveTbOrderService(TbOrder tbOrder,
                                   List<TbOrderItem> orderItems, TbOrderShipping tbOrderShipping) {
        try {
            tbOrderMapper.insert(tbOrder);
            for (TbOrderItem tbOrderItem:orderItems){
                tbOrderItemMapper.insert(tbOrderItem);
            }
            tbOrderShippingMapper.insert(tbOrderShipping);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<TbOrder> loadTbOrderListService(Long id) {

        //创建TbOrderExample对象
        TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        //where User_id=id
        criteria.andUserIdEqualTo(id);
        return tbOrderMapper.selectByExample(example);

    }

    @Override
    public List<TbOrderItem> loadTbOrderItemListService(String orderid) {

        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        //where order_id = orderid;
        criteria.andOrderIdEqualTo(orderid);
        return tbOrderItemMapper.selectByExample(example);

    }
}
