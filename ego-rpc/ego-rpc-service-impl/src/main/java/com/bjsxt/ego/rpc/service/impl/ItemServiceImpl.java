package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbItemDescMapper;
import com.bjsxt.ego.rpc.mapper.TbItemMapper;
import com.bjsxt.ego.rpc.mapper.TbItemParamItemMapper;
import com.bjsxt.ego.rpc.pojo.*;
import com.bjsxt.ego.rpc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 9:07
 */
@Service
public class ItemServiceImpl implements ItemService {
    //注入mapper接口代理对象
    //注入商品表的接口对象
    @Autowired
    private TbItemMapper tbItemMapper;
    //注入商品描述表的接口对象
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    /**
     * 商品分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResult<TbItem> selectItemList(Integer page, Integer rows) {

        //执行分页操作
        Page page1 = PageHelper.startPage(page, rows);

        TbItemExample example = new TbItemExample();

        //执行数据库查询操作,example：动态封装查询条件的
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);

        PageResult<TbItem> result = new PageResult<TbItem>();

        result.setRows(tbItems);
        result.setTotal(page1.getTotal());

        return result;
    }

    /**
     * 商品状态更新
     * @param itemIds:商品ID的集合，因为前端可以多选商品
     * @param flag:true,表示上架，false，代表下架
     * @return
     */
    @Override
    public EgoResult updataItemStatus(List<Long> itemIds, Boolean flag) {

        //创建商品的对象
        TbItem item=new TbItem();
        if(flag){//如果是true就表示上架
            item.setStatus((byte) 1);
        }else {
            item.setStatus((byte) 2);
        }
        //动态产生where条件 where id in(3,4,5)
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(itemIds);


        //updateByExample:动态的生成where条件---where id in(3,4,5)
        tbItemMapper.updateByExampleSelective(item,example);
        return EgoResult.ok();
    }

    @Override
    public EgoResult deleteItem(List<Long> itemIds) {
        //where id in (3,4,5)
        //动态产生where条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(itemIds);

        tbItemMapper.deleteByExample(example);
       return EgoResult.ok();
    }

    @Override
    public EgoResult saveItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamItem) {

        tbItemMapper.insert(item);//插入商品
        tbItemDescMapper.insert(desc);//插入商品描述信息d
        tbItemParamItemMapper.insert(itemParamItem);
        return EgoResult.ok();
    }

    /**
     * 更新商品信息
     * @param item：商品对象
     * @param desc：商品描述对象
     * @return
     */
    @Override
    public EgoResult updateItem(TbItem item, TbItemDesc desc) {
        //更新商品基本信息
        //updateByPrimaryKeySelective:如果某个特定的属性列是空的则不会进行更新
        //updateByPrimaryKey:表示对所有属性进行更新
        this.tbItemMapper.updateByPrimaryKeySelective(item);

        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(desc.getItemId());

        //where itemId = ?
        //查询某个商品对应的描述信息,统计where条件所满足的记录数
        Integer rows = tbItemDescMapper.countByExample(example);
        //判断该商品是否存在描述信息
        if(rows==0){
            //如果没有描述信息则执行添加操作
            this.tbItemDescMapper.insert(desc);
        }else {
            //如果有描述信息则设为空
            desc.setCreated(null);
            //如果这列为空则不会进行更新
            this.tbItemDescMapper.updateByPrimaryKeySelective(desc);
        }

        return EgoResult.ok();
    }
}
