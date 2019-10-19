package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;

/**
 * @author lvyelanshan
 * @create 2019-10-19 11:23
 */
public interface ManagerItemDescService {

    /**
     * 获得需要回显的商品描述信息
     * @param itemId
     * @return
     */
    public EgoResult getItemDescService(Long itemId);
}
