package com.bjsxt.ego.sso.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.service.TbUserService;
import com.bjsxt.ego.sso.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

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
    public EgoResult selectUser(String username, String password) {
        TbUser tbUser = tbUserServiceProxy.selectUserByUserName(username);
        if (tbUser!=null){
            //不是空的说明查询到了
            //因为数据库中的密码是进行加密后的，
            // 所以这里需要对前端提交的密码进行再次加密，匹配数据库中的密码
            password=DigestUtils.md5DigestAsHex(password.getBytes());
            if(password.equals(tbUser.getPassword())){
                JsonUtils.
                 String token = UUID.randomUUID().toString();
                //将用户信息保存到Redis数据库
                cluster.set(token,);
            }
        }
    }
}
