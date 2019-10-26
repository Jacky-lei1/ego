package com.bjsxt.ego.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lvyelanshan
 * @create 2019-10-25 15:09
 */
@Controller
public class PageController {

    /**
     * 进行页面跳转
     */
    @RequestMapping("/{url}")
    public String loadpage(@PathVariable String url){
        return url;
    }
}
