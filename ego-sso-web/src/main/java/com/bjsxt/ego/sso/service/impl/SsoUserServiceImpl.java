package com.bjsxt.ego.sso.service.impl;

import com.bjsxt.ego.beans.CookieUtils;
import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.service.TbUserService;
import com.bjsxt.ego.sso.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author lvyelanshan
 * @create 2019-10-24 20:16
 */
@Service
public class SsoUserServiceImpl implements SsoUserService {

    //注入远程服务对象
    @Autowired(required = false)
    private TbUserService tbUserServiceProxy;
    @Autowired
    private JedisCluster cluster;


    @Override
    public EgoResult loadUserByCondService(String cond, Integer type) {
        return tbUserServiceProxy.loadTbUserByCondService(cond,type);
    }

    @Override
    public EgoResult saveUserService(TbUser tbUser) {
        String pwd = tbUser.getPassword();//取出页面输入的原来的密码
        String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes());//对原来的密码进行Md5加密
        tbUser.setPassword(md5);//将加密后的密码保存到数据库中
        return tbUserServiceProxy.saveUserTbService(tbUser);
    }

    @Override
    public EgoResult selectUser(String username, String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        TbUser tbUser = tbUserServiceProxy.selectUserByUserName(username);
        System.out.println("服务消费者获取到的tbUser：---->"+tbUser);

        EgoResult result = new EgoResult();

        if (tbUser!=null){
            //不是空的说明查询到了
            //因为数据库中的密码是进行加密后的，
            // 所以这里需要对前端提交的密码进行再次加密，匹配数据库中的密码
            password=DigestUtils.md5DigestAsHex(password.getBytes());
            if(password.equals(tbUser.getPassword())){//登陆成功！！！
                //将当前登陆用户对象，转化为json字符串，保存到Redis数据库
                String json = JsonUtils.objectToJson(tbUser);
                //随机生成的一个token值作为key
                String token = UUID.randomUUID().toString();
                //将用户信息保存到Redis数据库
                cluster.set(token,json);
                //设置用户登陆的有效期,超过30分钟就会自动消失
                cluster.expire(token,1800);

                //将token响应到客户端,登陆成功后，刷新登陆页面，
                // 客户端会拿着这个token也就是Redis数据库中的key,找到对应的信息，响应到客户端中
                //将这个token保存到cookie中，今后就可以通过cookie的名字sso_token，拿到对应的token
                //从Redis数据库中查找，判断是否能找到，从而验证是否登陆
                CookieUtils.setCookie(request,response,"sso_token",token);

                result.setStatus(200);
                result.setMsg("ok");
                result.setData(token);
                System.out.println("服务消费者封装后的EgoResult:--->"+result);
                return result;
            }
        }
        //如果没有查询到
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    @Override
    public EgoResult loadUserByToken(String token) {
        //创建EgoResult
        EgoResult result = new EgoResult();
        String jsonStr = cluster.get(token);
        if(!StringUtils.isEmpty(jsonStr)){
            //如果不是空的，就说明查到了
            result.setStatus(200);
            result.setMsg("ok");
//            result.setData(jsonStr);
            //将jsonStr转化为TBUser对象
            TbUser tbUser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
            //这样在前台获取用户登陆状态的时候就能通过TBuser对象直接获取到
            result.setData(tbUser);
            return result;
        }
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    @Override
    public EgoResult deleteUserByToken(String token) {

        //创建一个EgoResult对象
        EgoResult result = new EgoResult();

        //删除Redis数据
        Long del = cluster.del(token);
        if(!del.equals(0L)){//如果不等于0说明删除成功
            result.setStatus(200);
            result.setMsg("ok");
            result.setData("");
            return result;
        }
        //如果等于0说明一个都没删
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);
        return result;
    }
}
