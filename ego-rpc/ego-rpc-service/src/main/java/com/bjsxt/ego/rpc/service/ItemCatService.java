package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.pojo.TbItemCat;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 16:40
 */
public interface ItemCatService {

    /**
     * 根据某个节点的id，查询对应的子节点的集合
     * @param id
     * @return
     */
    public List<TbItemCat> getItemCatListByParentId(Long id);

    /**
     * 加载门户首页的商品类目
     * @return
     */
    public List<TbItemCat> loadItemCatListService();

}
