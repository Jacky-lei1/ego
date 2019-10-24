package com.bjsxt.ego.search.service;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.search.entity.SearchResult;

/**
 * @author lvyelanshan
 * @create 2019-10-23 22:22
 */
public interface SearchItemService {

    /**
     * 进行商品信息关键字查询,分页查询
     * @param item_keywords
     * @param page
     * @return
     */
    public SearchResult loadItemService(String item_keywords, Integer page);

    /**
     * 根据ID查询商品的基本信息
     * @param id
     * @return
     */
    public TbItem loadItemService(Long id);
}
