package com.bjsxt.ego.portal.service;

/**
 * @author lvyelanshan
 * @create 2019-10-23 10:17
 */
public interface PortalContentService {
    /**
     * 返回某个内容类目，对应的内容数据（加载大广告实现图片轮播）
     */
    public String loadContentListByCidService(Long cid);

}
