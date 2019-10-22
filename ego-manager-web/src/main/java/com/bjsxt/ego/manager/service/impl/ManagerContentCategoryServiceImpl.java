package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerContentCategoryService;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.service.TbContentCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 8:33
 */
@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {

    //注入远程服务代理对象
    @Autowired(required = false)
    private TbContentCateGoryService tbContentCateGoryServiceProxy;

    @Override
    public List<TreeNode> loadContentCategoryService(Long pid) {
        List<TreeNode> list = new ArrayList<>();

        List<TbContentCategory> clist = tbContentCateGoryServiceProxy.loadTbContentCateGoryByPidService(pid);
        //将clist中的集合封装到TreeNode中去
        for(TbContentCategory c:clist){
            //创建ThreeNode对象
            TreeNode node  = new TreeNode();
            node.setId(c.getId());
            node.setText(c.getName());
            //如果是父节点则close，如果是子节点则打开
            node.setState(c.getIsParent()?"closed":"open");

            list.add(node);
        }
        return list;
    }

    @Override
    public EgoResult saveContentCategoryService(TbContentCategory contentCategory) {

        //封装TBContentCategory对象
        //创建Date对象
        Date date = new Date();
        //生成ID
        Long id = IDUtils.genItemId();
        //工具类生成的ID
        contentCategory.setId(id);
        //节点创建时间
        contentCategory.setCreated(date);
        //节点更新时间
        contentCategory.setUpdated(date);
        //节点状态
        contentCategory.setStatus(1);
        //节点排序
        contentCategory.setSortOrder(1);
        //新增的节点肯定是个子节点，所以默认为false
        contentCategory.setIsParent(false);
        return tbContentCateGoryServiceProxy.saveTbContentCateGory(contentCategory);
    }

    @Override
    public void deleteContentCategoryService(Long id) {
        tbContentCateGoryServiceProxy.deleteTbContentCateGoryService(id);
    }
}
