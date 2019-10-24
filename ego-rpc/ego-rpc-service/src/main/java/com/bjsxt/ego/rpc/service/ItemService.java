package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 9:02
 */
public interface ItemService {
    /**
     * 实现商品信息的分页查询
     */
    public PageResult<TbItem> selectItemList(Integer page, Integer rows);

    /**
     * 完成商品上下架状态的修改,根据商品ID来修改，
     * 而商品ID是long类型的主键
     * @param itemIds:商品ID的集合，因为前端可以多选商品
     * @param flag:true,表示上架，false，代表下架
     * @return
     */
    public EgoResult updataItemStatus(List<Long> itemIds,Boolean flag);


    /**
     * 删除商品信息
     * @param itemIds 需要删除的商品信息的ID集合
     * @return
     */
    public EgoResult deleteItem(List<Long> itemIds);

    /**
     * 保存商品信息
     * @param item：商品对象
     * @param desc：商品描述对象
     * @param itemParamItem:商品模板信息表
     * @return
     */
    public EgoResult saveItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamItem);


    /**
     * 更新商品的信息
     * @param item：商品对象
     * @param desc：商品描述对象
     * @return
     */
    public EgoResult updateItem(TbItem item,TbItemDesc desc,TbItemParamItem itemParamItem);

    /**
     * 根据id，查询商品的详细信息
     * @param id
     * @return
     */
    public TbItem loadTbItemById(Long id);
}
