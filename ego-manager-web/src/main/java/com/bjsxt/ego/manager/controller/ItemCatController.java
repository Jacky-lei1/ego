package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 17:23
 */
@Controller
public class ItemCatController {
    //注入Service对象
    @Autowired
    private ManagerItemCatService managerItemCatService;

    /**
     * 处理加载商品类目的请求
     */
    @RequestMapping(value = "/item/cat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public List<TreeNode> itemCatList(@RequestParam(defaultValue = "0",required = false) Long id){
        return managerItemCatService.getItemCatList(id);

    }
}
