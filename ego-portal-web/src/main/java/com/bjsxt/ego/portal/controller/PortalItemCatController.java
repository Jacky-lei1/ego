package com.bjsxt.ego.portal.controller;

import com.bjsxt.ego.portal.service.PortalItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-22 22:06
 */
@Controller
public class PortalItemCatController {
    //注入Service对象
    @Autowired
    private PortalItemCatService portalItemCatService;

    /**
     * 处理加载商品类目的请求
     */
    @RequestMapping(value = "/item/cat",produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String itemCat(){
        return portalItemCatService.loadItemCatService();
    }

}
