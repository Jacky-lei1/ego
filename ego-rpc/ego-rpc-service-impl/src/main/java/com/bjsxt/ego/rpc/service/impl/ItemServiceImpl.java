package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbItemMapper;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemExample;
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
    @Autowired
    private TbItemMapper tbItemMapper;

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
}
