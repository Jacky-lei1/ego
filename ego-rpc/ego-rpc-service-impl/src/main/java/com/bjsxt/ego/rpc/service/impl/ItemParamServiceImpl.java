package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbItemParamMapper;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.pojo.TbItemParamExample;
import com.bjsxt.ego.rpc.service.ItemParamService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-19 14:59
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    /**
     * 商品规格参数模板的分页显示
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer rows) {
        PageResult<TbItemParam> result = new PageResult<>();
        //进行分页参数指定(用到了Mybatis的分页插件)
        Page pe = PageHelper.startPage(page, rows);

        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam>  list =  tbItemParamMapper.selectByExampleWithBLOBs(example);

        result.setRows(list);
        result.setTotal(pe.getTotal());

        System.out.println("ItemParamServiceImpl-------->loadTbItemParamListService----->\n");
        System.out.println(result);
        return result;
    }

    /**
     * 根据商品类名ID，获得该类名的规格参数模板对象
     * @param cid
     * @return
     */
    @Override
    public TbItemParam loadTbItemParamByCidService(Long cid) {

        TbItemParamExample example = new TbItemParamExample();
        //封装查询条件
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);

        //正确情况只能查出一条数据，因为每一个商品对应的规格模板都是唯一的
        if(list!=null&&list.size()==1){
            return list.get(0);
        }
        //其他情况都是不正确的
        return null;

    }

    @Override
    public EgoResult saveTbItemParamService(TbItemParam tbItemParam) {

        try {
            //如果添加没有问题，则响应状态200
            tbItemParamMapper.insertSelective(tbItemParam);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public EgoResult deleteTbItemParamService(List<Long> ids) {
        try {
            //创建Example对象
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            //封装删除条件:where id in(1,23,3)
            criteria.andIdIn(ids);

            //如果删除没有问题，则响应状态200
            tbItemParamMapper.deleteByExample(example);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
