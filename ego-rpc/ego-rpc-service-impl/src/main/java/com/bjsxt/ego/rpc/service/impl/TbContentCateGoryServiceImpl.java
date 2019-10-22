package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.mapper.TbContentCategoryMapper;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.pojo.TbContentCategoryExample;
import com.bjsxt.ego.rpc.service.TbContentCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 8:25
 */
@Service
public class TbContentCateGoryServiceImpl implements TbContentCateGoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid) {

        //创建Example对象
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        //where parent_id = ?
        criteria.andParentIdEqualTo(pid);

        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);

        return list;
    }

    @Override
    public EgoResult saveTbContentCateGory(TbContentCategory contentCategory) {

        EgoResult result = null;

        try {
            //TbContentCategory对象
            TbContentCategory recordExtractor = new TbContentCategory();
            recordExtractor.setIsParent(true);
            //更新当前节点的父节点的ID
            recordExtractor.setId(contentCategory.getParentId());
            //更新当前添加的节点父节点的is_parent,原来如果是个叶子节点就更新为true，如果原来就是父节点，那么久再更新一次，不用去判断
            tbContentCategoryMapper.updateByPrimaryKeySelective(recordExtractor);
            //添加内容分类节点
            tbContentCategoryMapper.insert(contentCategory);

            result = new EgoResult();
            result.setStatus(200);
            result.setData(contentCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteTbContentCateGoryService(Long id) {
        //更新当前点击的节点的父节点is_parent

        //获得当前点击的节点的父节点ID
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        Long pid = tbContentCategory.getParentId();
        //根据pid查询pid对应的节点是否有子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        //where parent_id = ?
        criteria.andParentIdEqualTo(pid);
        //计算获取到的查询条数
        int count = tbContentCategoryMapper.countByExample(example);
        if(count==1){//如果当前的节点只有一个子节点，则需要将当前节点父节点的is_parent属性改为false
            // 更新当前需要删除的节点的父节点的is_parent
            TbContentCategory record = new TbContentCategory();
            //需要根据ID定位到当前的父节点的条目
            record.setId(pid);
            record.setIsParent(false);
            /*updateByPrimaryKeySelective：表示有空的不更新，只更新有设置属性的*/
            tbContentCategoryMapper.updateByPrimaryKeySelective(record);
        }

        //删除当前选中的节点
        tbContentCategoryMapper.deleteByPrimaryKey(id);


    }
}
