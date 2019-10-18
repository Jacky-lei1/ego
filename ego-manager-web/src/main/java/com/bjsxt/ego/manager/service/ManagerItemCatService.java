package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.TreeNode;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 17:01
 */
public interface ManagerItemCatService {
    /**
     * 根据节点id，加载当前节点的所有子节点集合
     * @param id
     * @return
     */
    public List<TreeNode> getItemCatList(Long id);
}
