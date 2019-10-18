package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerItemCatService;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 17:05
 */
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

    //注入远程服务的代理对象,required = false表示有对象就注入，没有就忽略
    @Autowired(required = false)
    private ItemCatService itemCatServiceProxy;

    @Override
    public List<TreeNode> getItemCatList(Long id) {
        List<TbItemCat> list = itemCatServiceProxy.getItemCatListByParentId(id);
        //将返回的原始集合转换为TreeNode集合
        List<TreeNode> nodeList = new ArrayList<TreeNode>();

        TreeNode node = null;
        for(TbItemCat cat:list){
            node = new TreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent()?"closed":"open");
            nodeList.add(node);
        }
        return nodeList;
    }
}
