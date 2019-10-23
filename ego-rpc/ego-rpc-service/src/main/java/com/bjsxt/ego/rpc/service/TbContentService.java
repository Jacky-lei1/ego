package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbContent;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 21:36
 */
public interface TbContentService {
    /**
     * 根据内容分类的ID，查询该分类下的所有内容
     * @param cid
     * @return
     */
    public PageResult<TbContent> loadTbContentListService(Long cid,Integer page,Integer rows);

    /**
     * 完成内容的添加
     * @param tbContent
     * @return
     */
    public EgoResult saveTbContentService(TbContent tbContent);

    /**
     * 实现内容的批量删除
     * @param ids
     * @return
     */
    public EgoResult deleteTbContentService(List<Long> ids);

    /**
     * 完成广告内容的更新
     * @param tbContent
     * @return
     */
    public EgoResult updateTbContentService(TbContent tbContent);

    /**
     * 加载某个类目对应的内容列表（根据ID查询大广告位的信息，实现主页图片的轮播：tb_content表）
     * @param id
     * @return
     */
    public List<TbContent> loadTbContentListByCidService(Long id);
}
