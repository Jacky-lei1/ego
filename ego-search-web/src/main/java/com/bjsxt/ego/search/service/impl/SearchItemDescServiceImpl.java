package com.bjsxt.ego.search.service.impl;

import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.service.ItemDescService;
import com.bjsxt.ego.search.entity.Item;
import com.bjsxt.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lvyelanshan
 * @create 2019-10-24 16:48
 */
@Service
public class SearchItemDescServiceImpl implements SearchItemDescService {
    //注入远程服务代理对象
    @Autowired(required = false)
    private ItemDescService itemDescServiceProxy;
    @Override
    public String loadItemDescService(Long id) {

        TbItemDesc itemDesc = itemDescServiceProxy.getItemDesc(id);

        //服务提供者中返回的是一个对象，但服务消费者只需要具体的描述信息，其他的不需要，
        //所以返回的只是一个字符串，对应数据表中是一个HTML片段
        return itemDesc.getItemDesc();
    }
}
