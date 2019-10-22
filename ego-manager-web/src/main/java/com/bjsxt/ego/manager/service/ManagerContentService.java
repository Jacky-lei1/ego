package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbContent;

/**
 * @author lvyelanshan
 * @create 2019-10-21 22:13
 */
public interface ManagerContentService {
    /**
     * 根据内容分类的ID进行内容的分页查询
     */
    public PageResult loadContentListService(Long cid, Integer page, Integer rows);

    /**
     * 完成内容的添加
     */
    public EgoResult saveContentService(TbContent tbContent);

    /**
     * 实现内容批量删除
     */
    public EgoResult deleteContentService(String ids);

    /**
     * 实现内容数据的更新操作
     */
    public EgoResult updateContentService(TbContent tbContent);

}
