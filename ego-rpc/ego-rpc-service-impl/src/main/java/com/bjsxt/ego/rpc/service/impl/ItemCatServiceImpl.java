package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.rpc.mapper.TbItemCatMapper;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.pojo.TbItemCatExample;
import com.bjsxt.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 16:45
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    //注入Mapper接口代理对象
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> getItemCatListByParentId(Long id) {

        //创建TbItemCatExample对象
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //where parent_id=?
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(example);
        return tbItemCats;
    }

    @Override
    public List<TbItemCat> loadItemCatListService() {

        TbItemCatExample example = new TbItemCatExample();

        return tbItemCatMapper.selectByExample(example);
    }
}
