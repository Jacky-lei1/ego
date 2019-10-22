package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;

/**
 * @author lvyelanshan
 * @create 2019-10-20 20:39
 */
public interface ManagerParamItemService {

    /**
     * 根据商品ID加载商品规格参数
     * @param itemid
     * @return
     */
    public EgoResult loadParamItemService(Long itemid);
}
