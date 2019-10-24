package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;

/**
 * @author lvyelanshan
 * @create 2019-10-24 19:46
 */
public interface TbUserService {
    /**
     * 实现用户名唯一性验证
     * @param cond 验证数据
     * @param type 验证类型（前台收到的数据是属于姓名，电话还是邮箱地址）
     * @return
     */
    public EgoResult loadTbUserByCondService(String cond,Integer type);

    /**
     * 实现用户注册
     * @param tbUser
     * @return
     */
    public EgoResult saveUserTbService(TbUser tbUser);


    /**
     * 进行用户登录账户的验证
     * @param uname
     * @return
     */
    public TbUser selectUserByUserName(String uname);
}
