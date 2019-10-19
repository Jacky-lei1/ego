package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerItemParamService;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-19 15:19
 */
@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

    //注入服务的远程代理对象
    @Autowired(required = false)
    private ItemParamService itemParamServiceProxy;

    @Override
    public PageResult<TbItemParam> loadItemParamListService(Integer page, Integer rows) {
        return itemParamServiceProxy.loadTbItemParamListService(page,rows);
    }

    @Override
    public EgoResult loadItemParamByCidService(Long cid) {
        EgoResult result = null;

        try {
            //TbItemParam:商品规格模板实体类，
            // 根据前端传来的商品ID查询对应的实体表中
            // 是否有该商品ID对应的模板信息模板信息，商品表与商品信息模板表时一对一的关系，
            //也就是说一个商品只能对应一个模板，如果没有找到就返回空(啥都不显示)
            TbItemParam tbItemParam = itemParamServiceProxy.loadTbItemParamByCidService(cid);
            result = new EgoResult();
            result.setStatus(200);
            //将查询到的数据封装到EgoResult中
            result.setData(tbItemParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有出现异常，那么就正常返回
//        System.out.println("ManagerItemParamServiceImpl------->\n");
//        System.out.println(result);
        return result;
    }

    /**
     * 添加商品参数规格模板信息
     * @param cid：前端传来的参数
     * @param paramDate：前端传来的表中的数据
     * @return
     */
    @Override
    public EgoResult saveItemParamService(Long cid, String paramDate) {

        //补齐商品信息模板表实体类信息，并封装成实体类对象
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramDate);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());

        //调用远程服务,如果没问题返回一个EgoResult对象，如果有问题返回一个空
       return itemParamServiceProxy.saveTbItemParamService(tbItemParam);
    }

    @Override
    public EgoResult deleteItemParamService(Long[] ids) {
        //将Long类型数组转换为List集合
        List<Long> id = Arrays.asList(ids);
        return itemParamServiceProxy.deleteTbItemParamService(id);
    }
}
