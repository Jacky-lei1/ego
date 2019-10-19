package com.bjsxt.ego.manager.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerItemParamService;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvyelanshan
 * @create 2019-10-19 15:24
 */
@Controller
public class ItemParamController {
    //注入本地的service对象
    @Autowired
    private ManagerItemParamService managerItemParamService;

    /**
     * 处理规格参数模板的分页请求
     */
    //produces：返回json数据的字符编码为UTF-8
    @RequestMapping(value = "/item/param/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    //返回json对象，默认是第一页，返回30条数据
    @ResponseBody
    public PageResult<TbItemParam> itemParamList(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "30") Integer rows){
        PageResult<TbItemParam> result = managerItemParamService.loadItemParamListService(page, rows);
//        System.out.println("ItemParamController----->itemParamList------->\n");
//        System.out.println(result);
        return result;

    }

    /**
     * 处理根据商品类目ID查询规格参数模板的请求
     * @return
     */
    //produces：返回json数据的字符编码为UTF-8
    @RequestMapping(value = "/item/param/query/{cid}",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemParamQuery(@PathVariable Long cid){
        EgoResult result = managerItemParamService.loadItemParamByCidService(cid);
//        System.out.println(result);
        return result;
    }

    /**
     * 处理新增商品规格参数模板的请求
     * 根据当前选择的商品ID添加模板信息
     * @param cid
     * @param paramData
     * @return
     */
    @RequestMapping(value = "/item/param/save/{cid}",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult saveItemParam(@PathVariable Long cid,String paramData){
        System.out.println("ItemParamController"+"-------->"+paramData);
        return managerItemParamService.saveItemParamService(cid,paramData);
    }

    /**
     * 处理商品规格参数模板批量删除的请求
     * @param
     * @param
     * @return
     */

    @RequestMapping(value = "/item/param/delete",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult deleteItemParam(Long[] ids){
        return managerItemParamService.deleteItemParamService(ids);
    }



    /**
     * 处理根据商品类目ID查询规格参数模板的请求
     * @return
     */
    //produces：返回json数据的字符编码为UTF-8
    @RequestMapping(value = "/item/param/select/{cid}",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemParamSelect(@PathVariable Long cid){
        EgoResult result = managerItemParamService.loadItemParamByCidService(cid);
//        System.out.println(result);
        return result;
    }


}
