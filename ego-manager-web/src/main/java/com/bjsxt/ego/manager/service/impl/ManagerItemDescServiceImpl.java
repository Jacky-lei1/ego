package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerItemDescService;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lvyelanshan
 * @create 2019-10-19 11:24
 */
@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {
    //注入远程服务的代理对象
    @Autowired(required = false)
    private ItemDescService itemDescServiceProxy;
    @Override
    public EgoResult getItemDescService(Long itemId) {
        //调用远程服务
        TbItemDesc desc = itemDescServiceProxy.getItemDesc(itemId);
        if (desc!=null){
            return new EgoResult(desc);//前台需要返回查询到的data数据
        }else{
            return new EgoResult(null);//如果没有查询到则传入一个空的参数
        }
    }
}
