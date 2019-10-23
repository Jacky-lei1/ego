package com.bjsxt.ego.search.service;

import com.bjsxt.ego.search.entity.SearchResult;

/**
 * @author lvyelanshan
 * @create 2019-10-23 22:22
 */
public interface SearchItemService {

    /**
     * 进行商品信息关键字查询
     * @param item_keywords
     * @param page
     * @return
     */
    public SearchResult loadItemService(String item_keywords, Integer page);
}
