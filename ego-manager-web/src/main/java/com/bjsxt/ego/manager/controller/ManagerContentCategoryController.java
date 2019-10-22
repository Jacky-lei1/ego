package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerContentCategoryService;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 9:31
 */
@Controller
public class ManagerContentCategoryController {
    //注入本地的service对象
    @Autowired
    private ManagerContentCategoryService managerContentCategoryService;
    /**
     * 处理加载内容分类树的请求
     */
    @RequestMapping(value = "/content/category/list",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public List<TreeNode> contentCategroyList(@RequestParam(defaultValue = "0") Long id){
        return managerContentCategoryService.loadContentCategoryService(id);
    }

    /**
     * 处理添加内容分类节点的请求
     * @param
     * @return
     */

    @RequestMapping(value = "/content/category/create",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult contentCategroyCreate(TbContentCategory contentCategory){
        return managerContentCategoryService.saveContentCategoryService(contentCategory);
    }

    /**
     * 处理删除内容分类节点的请求
     * @param id
     * @return
     */

    @RequestMapping(value = "/content/category/delete",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public String contentCategroyDelete(Long id){
        managerContentCategoryService.deleteContentCategoryService(id);
        return null;
    }

}
