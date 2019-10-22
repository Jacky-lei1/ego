package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.rpc.mapper.TbItemParamItemMapper;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.pojo.TbItemParamItemExample;
import com.bjsxt.ego.rpc.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-20 20:28
 */
@Service
public class ParamItemServiceImpl implements ParamItemService {

    /**
     * 根据商品ID查询对应的商品规格参数信息
     */
    //注入Mapper代理对象
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public TbItemParamItem loadTbItemParamService(Long itemid) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        //封装查询条件
        criteria.andItemIdEqualTo(itemid);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list!=null && list.size()==1)
            return list.get(0);
        return null;
    }
}
