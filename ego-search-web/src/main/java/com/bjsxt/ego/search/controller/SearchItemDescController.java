package com.bjsxt.ego.search.controller;

import com.bjsxt.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-24 16:52
 */
@Controller
public class SearchItemDescController {
    //注入一个Service对象
    @Autowired
    private SearchItemDescService searchItemDescService;
    /**
     * 处理加载商品描述信息的请求
     */
    @RequestMapping(value = "/item/desc/{id}",produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String loadItemDesc(@PathVariable Long id){
        return searchItemDescService.loadItemDescService(id);
    }
}
