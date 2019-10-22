package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 22:15
 */
@Service
public class ManagerContentServiceImpl implements ManagerContentService {
    @Autowired(required = false)
    private TbContentService tbContentServiceProxy;


    @Override
    public PageResult<TbContent> loadContentListService(Long cid, Integer page, Integer rows) {
        return tbContentServiceProxy.loadTbContentListService(cid,page,rows);
    }

    @Override
    public EgoResult saveContentService(TbContent tbContent) {

        //封装TBContent对象
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);

        return tbContentServiceProxy.saveTbContentService(tbContent);
    }

    @Override
    public EgoResult deleteContentService(String ids) {
        //前端封装的数据是一个用逗号分隔的字符串数据，所以需要单独将这些ID取出来
        String[] idss = ids.split(",");
        //将idss转化为List<Long>,因为后台数据库中保存的ID是long类型的
        //创建List集合对象
        List<Long> list = new ArrayList<>();
        for (String id:idss){
            list.add(Long.parseLong(id));
        }
        return tbContentServiceProxy.deleteTbContentService(list);
    }

    @Override
    public EgoResult updateContentService(TbContent tbContent) {

        Date date = new Date();
        tbContent.setUpdated(date);

        return tbContentServiceProxy.updateTbContentService(tbContent);
    }
}
