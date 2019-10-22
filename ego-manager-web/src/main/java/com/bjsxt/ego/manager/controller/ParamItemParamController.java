package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-20 20:50
 */
@Controller
public class ParamItemParamController {
    @Autowired
    private ManagerParamItemService managerParamItemService;
    /**
     * 处理加载商品规格参数规格信息的请求
     */
    @RequestMapping(value = "/param/item/query/{itemid}",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult paramItemQuery(@PathVariable Long itemid){
        EgoResult result = managerParamItemService.loadParamItemService(itemid);
        System.out.println("传入的商品查询ID:"+itemid);
        System.out.println("加载的商品参数信息:"+result);
        return result;
    }

}
