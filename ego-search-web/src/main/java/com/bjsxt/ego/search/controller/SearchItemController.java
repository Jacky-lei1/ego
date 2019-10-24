package com.bjsxt.ego.search.controller;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.search.entity.SearchResult;
import com.bjsxt.ego.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @author lvyelanshan
 * @create 2019-10-23 19:36
 */
@Controller
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;
    /**
     * 进行页面跳转
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url, String q, @RequestParam(defaultValue = "1") Integer page, Model model){
        //调用业务层方法
        String kws = null;
        try {
            kws = new String(q.getBytes("ISO-8859-1"),"UTF-8");
            SearchResult result = searchItemService.loadItemService(kws, page);
            model.addAttribute("query",kws);
            model.addAttribute("itemList",result.getList());
            model.addAttribute("page",page);
            model.addAttribute("maxpage",result.getMaxpage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url;
    }
    /*@RequestMapping("/item")
    public String item(){
        return "item";
    }*/
    /**
     * 处理加载商品基本信息的请求
     */
    @RequestMapping("/item/{id}")
    public String loadItem(@PathVariable Long id,Model model){
        TbItem item = searchItemService.loadItemService(id);
        model.addAttribute("item",item);
        return "item";

    }
}
