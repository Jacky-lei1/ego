package com.bjsxt.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lvyelanshan
 * @create 2019-10-24 19:36
 */
@Controller
public class PageController {
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url){
        return url;
    }
}
