package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-18 11:12
 */
@Controller
public class ItemController {
    //注入Service对象
    @Autowired
    private ManagerItemService managerItemService;

    /**
     * 处理商品信息分页查询的请求
     */
    //produces：返回json数据的字符编码为UTF-8
    @RequestMapping(value = "item/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    //返回json对象，默认是第一页，返回30条数据
    @ResponseBody
    public PageResult<TbItem> itemList(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "30") Integer rows){
        return managerItemService.selectItemListService(page,rows);
    }

    /**
     * 商品的上架
     * @param ids
     * @return
     */
    @RequestMapping(value = "item/reshelf",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult reshelfItem(Long[] ids){
        return managerItemService.reshelfItem(ids);

    }

    /**
     * 商品的下架
     * @param ids
     * @return
     */
    @RequestMapping(value = "item/instock",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult instockItem(Long[] ids){
        return managerItemService.instockItem(ids);

    }

    /**
     * 处理商品信息的删除请求
     * produces:指定响应回去的数据类型和编码格式
     */
    @RequestMapping(value = "item/delete",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemDelete(Long[] ids){
        //这里调用的是本地的方法，在实现类中调用的是远程的服务
        EgoResult egoResult = managerItemService.deleteItemService(ids);
        return egoResult;
    }


    /**
     * 处理商品信息发布的请求
     */
    @RequestMapping(value = "item/save",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemSave(TbItem item,String desc,String itemParams){
        return managerItemService.saveItemService(item,desc,itemParams);
    }


    /**
     * 处理商品信息的更新请求
     */
    @RequestMapping(value = "item/update",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemUpdate(TbItem item,String desc){
        return managerItemService.updateItemService(item,desc);
    }


}
