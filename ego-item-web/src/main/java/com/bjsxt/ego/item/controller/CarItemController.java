package com.bjsxt.ego.item.controller;

import com.bjsxt.ego.item.entity.CarItem;
import com.bjsxt.ego.item.service.CarItemService;
import com.bjsxt.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.rmi.server.UID;
import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-25 16:33
 */
@Controller
public class CarItemController {

    @Autowired
    private CarItemService carItemService;
    /**
     * 处理将商品添加到购物车的请求
     */
    @RequestMapping("/cart/add/{itemid}")
    public String carrAdd(@PathVariable Long itemid,
                          HttpServletRequest request){
        //因为每次请求都要进过拦截器，在拦截器中已经将用户对象保存到了request作用域中
        TbUser user = (TbUser) request.getAttribute("user");
        carItemService.addItemToCarService(itemid,user.getId());
        return "cartSuccess";
    }
    /**
     * 处理加载用户购物车集合的请求
     */
    @RequestMapping("/cart/cart")
    public String loadCarItemList(HttpServletRequest request){
        //获得当前用户的id
        TbUser tbUser = (TbUser)request.getAttribute("user");
        Long uid = tbUser.getId();
        Map<Long, CarItem> carMap = carItemService.loadCarItemListService(uid);
        //将查询到的购物车集合对象放入request作用域中，展示给用户
        request.setAttribute("carMap",carMap);

        return "cart";
    }
    /**
     * 处理更新购物车数量的请求
     * request对象获取到用户的id
     */
    @RequestMapping("/cart/update/num/{itemid}/{num}")
    @ResponseBody
    public String cartUpdateNum(@PathVariable Long itemid,
                                @PathVariable Integer num,HttpServletRequest request){
        //通过request对象获取到uid
        TbUser tbUser = (TbUser) request.getAttribute("user");
        return carItemService.updateCarItemNumService(itemid,tbUser.getId(),num);
    }

    /**
     * 处理删除商品购物车的请求
     */
    @RequestMapping("/cart/delete/{itemid}")
    public String cartDelete(@PathVariable Long itemid,HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        Long uid = user.getId();
        carItemService.deleteCarItemService(itemid,uid);
        //删除成功后重定向到加载购物车的页面中
        return "redirect:/cart/cart.html";
    }

    /**
     * 处理清空用户购物车请求
     */
    @RequestMapping("delete/cart/all")
    public String deleteCartAll(HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        Long id = user.getId();
        carItemService.deleteCarItemAllService(id);
        return "redirect:/cart/cart.html";
    }

}
