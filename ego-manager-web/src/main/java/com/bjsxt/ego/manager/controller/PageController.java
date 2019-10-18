package com.bjsxt.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lvyelanshan
 * @create 2019-10-18 8:32
 */
@Controller
public class PageController {
    /**
     * 加载商城后台的首页
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    /**
     * 加载其他的jsp视图
     */
    @RequestMapping("{page}")
    public String showPage(@PathVariable String page){
        return page;

    }
}
