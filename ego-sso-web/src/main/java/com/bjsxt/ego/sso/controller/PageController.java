package com.bjsxt.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lvyelanshan
 * @create 2019-10-24 19:36
 */
@Controller
public class PageController {
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url,
                           @RequestParam(required = false) String redirect,
                           Model model){
        //将拦截中未登录时发送的请求的URL中的redirect放入模型类中，响应到前端，前端通过判断跳转到相应的页面
        model.addAttribute("redirect",redirect);
        return url;
    }
}
