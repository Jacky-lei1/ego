package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbItemParam;

/**
 * @author lvyelanshan
 * @create 2019-10-19 15:16
 */
public interface ManagerItemParamService {
    /**
     * 实现商品规格参数的分页查询
     * @param page
     * @param rows
     * @return
     */
    public PageResult<TbItemParam> loadItemParamListService(Integer page,Integer rows);

    /**
     * 根据商品类id，查询商品类目对应的规格参数模板
     * @param cid
     * @return
     */
    public EgoResult loadItemParamByCidService(Long cid);

    /**
     * 添加商品参数规格模板信息
     * @param cid
     * @param paramDate
     * @return
     */
    public EgoResult saveItemParamService(Long cid,String paramDate);

    /**
     * 进行商品规格参数模板信息的批量删除
     * @param ids
     * @return
     */
    public EgoResult deleteItemParamService(Long[] ids);

}
