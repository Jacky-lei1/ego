package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-19 11:36
 */
@Controller
public class ItemDescController {
    //注入本地的Service对象，具体的远程调用服务在另一台服务器中
    @Autowired
    private ManagerItemDescService managerItemDescService;

    /**
     * 处理加载商品描述信息的请求
     * @param itemId
     * @return
     */
    @RequestMapping(value = "query/item/desc/{itemId}",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemDesc(@PathVariable Long itemId){
        return managerItemDescService.getItemDescService(itemId);
    }

}
