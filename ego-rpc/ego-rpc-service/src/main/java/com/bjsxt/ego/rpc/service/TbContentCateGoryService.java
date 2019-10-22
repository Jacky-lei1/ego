package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 8:21
 */
public interface TbContentCateGoryService {
    /**
     * 加载内容分类树
     * 根据父ID来查询子节点信息
     * 返回的是树节点对象的集合，
     * 这个树节点对象的集合最终被序列化成json串响应给客户端
     */
    public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid);

    /**
     * 添加内容分类的节点
     */
    public EgoResult saveTbContentCateGory(TbContentCategory contentCategory);

    /**
     * 根据ID删除内容分类节点
     * @param id
     */
    public void deleteTbContentCateGoryService(Long id);
}
