package com.bjsxt.ego.search.service;

/**
 * @author lvyelanshan
 * @create 2019-10-24 17:35
 */
public interface SearchItemParamService {
    /**
     * 加载商品的规格参数，返回的是HTML代码
     * @param id
     * @return
     */
    public String  loadItemParamService(Long id);
}
