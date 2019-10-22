package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-21 22:19
 */
@Controller
public class ManagerContentController {
    //注入service对象
    @Autowired
    private ManagerContentService managerContentService;

    /**
     * 处理商品广告内容分页查询请求
     */
    @RequestMapping(value = "/content/query/list",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public PageResult<TbContent> contentQueryList(Long categoryId,Integer page,Integer rows){
        return managerContentService.loadContentListService(categoryId,page,rows);
    }

    /**
     * 处理商品广告内容的添加请求
     */
    @RequestMapping(value = "/content/save",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult contentSave(TbContent tbContent){
        return managerContentService.saveContentService(tbContent);
    }


    /**
     * 处理商品广告内容的删除的请求
     */
    @RequestMapping(value = "/content/delete",
            produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult contentDelete(String ids){
        return managerContentService.deleteContentService(ids);
    }


    /**
     * 处理商品广告内容的更新
     * @param
     * @return
     */
    @RequestMapping(value = "/rest/content/edit",produces = MediaType.APPLICATION_JSON_VALUE+";chartset=UTF-8")
    @ResponseBody
    public EgoResult contentEdit(TbContent tbContent){
        return managerContentService.updateContentService(tbContent);
    }

}
