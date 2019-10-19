package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.rpc.pojo.TbItemDesc;

/**
 * 通过商品的ID，获得商品的描述信息
 * @author lvyelanshan
 * @create 2019-10-19 11:13
 */
public interface ItemDescService {
    public TbItemDesc getItemDesc(Long itemId);

}
