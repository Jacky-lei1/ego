package com.bjsxt.ego.sso.controller;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.sso.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author lvyelanshan
 * @create 2019-10-24 20:19
 */
@Controller
public class SsoUserController {
    //注入本地业务对象
    @Autowired
    private SsoUserService ssoUserService;
    /**
     * 处理用户名唯一性验证请求
     * @RequestParam(required = false):表示这个参数不是必须的
     * 也就是可以支持jsonp,也可以不支持jsonp
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue userCheck(@PathVariable String param,
                                         @PathVariable Integer type,
                                         @RequestParam(required = false) @PathVariable String callback){
        //前端如果发送的不是一个jsonp请求则直接这么返回即可
        EgoResult result = ssoUserService.loadUserByCondService(param, type);
        System.out.println("result=="+result);

        //如果前端发送的是一个jsop,就不能这么响应了
        MappingJacksonValue value = new MappingJacksonValue(result);
        //处理jsonp响应数据格式
        if(!StringUtils.isEmpty(callback)){
            //如果callback不是空的，则在不仅返回result，还返回一个回调函数的值
            //如果callback是空的，则只返回一个result的值
            value.setJsonpFunction(callback);
            //return value;
        }
        System.out.println("value=="+value);
       return value;
    }

    /**
     * 处理用户注册请求
     */
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public EgoResult userRegister(TbUser tbUser){
      return  ssoUserService.saveUserService(tbUser);
    }


}
