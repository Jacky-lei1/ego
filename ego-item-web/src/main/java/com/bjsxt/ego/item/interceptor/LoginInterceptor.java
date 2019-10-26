package com.bjsxt.ego.item.interceptor;

import com.bjsxt.ego.beans.CookieUtils;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器两种实现
 * 1、继承HandlerInterceptorAdapter
 * 2、实现HandlerInterceptor接口
 * @author lvyelanshan
 * @create 2019-10-25 15:23
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JedisCluster cluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获得token(用户登陆时，在cookie存入了token，通过sso_token名字，可以将这个token取出来，然后拿着这个token去和Redis数据库比对)
        String token = CookieUtils.getCookieValue(request, "sso_token");
        if(!StringUtils.isEmpty(token)){//判断token是否为空，token不为空才进行操作
            //拿着从cookie中获取到的token查询Redis数据库
            String jsonStr = cluster.get(token);
            //验证当前用户是否登陆
            if(!StringUtils.isEmpty(jsonStr)){//如果从cookie中获取到的值不是空的
                //将获取到用户数据转换成TBuser对象
                TbUser tbUser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
                //将这个用户对象保存到request作用域
                request.setAttribute("user",tbUser);

                //说明用户已经登陆，则放行
                return true;//请求什么就放行什么
            }
        }

        //如果没有获取到token，表示用户没有登录，则进行登录页面跳转即可
        //在用户登陆成功后跳转的路径,获取到用户点击添加购物车按钮后发起的请求路径
        String url = request.getRequestURL().toString();
        System.out.println("用户未登录时返回的URL：http://localhost:8083/login?redirect="+url);
        //重定向到登陆页面，登录成功后，如果redirect==null，则跳转到首页，如果不为空，则URL是什么就跳转到什么
        response.sendRedirect("http://localhost:8083/login?redirect="+url);

        return false;

    }
}
