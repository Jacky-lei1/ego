package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.rpc.mapper.TbItemDescMapper;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**通过ID查询商品描述信息
 * @author lvyelanshan
 * @create 2019-10-19 11:15
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    public TbItemDesc getItemDesc(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }
}
