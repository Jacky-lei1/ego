package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.mapper.TbUserMapper;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.pojo.TbUserExample;
import com.bjsxt.ego.rpc.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-24 19:48
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public EgoResult loadTbUserByCondService(String cond, Integer type) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //根据类型封装查询条件
        if (type.equals(1)){
            //如果类型是1则根据用户名来查询
            criteria.andUsernameEqualTo(cond);
        }else if(type.equals(2)){
            //如果类型是2则更加电话号码来查询
            criteria.andPhoneEqualTo(cond);
        }else {
            criteria.andEmailEqualTo(cond);
        }

        List<TbUser> list = tbUserMapper.selectByExample(example);
        //创建一个EgoResult对象
        EgoResult result = new EgoResult();
        result.setStatus(200);
        result.setMsg("ok");
        //判断list中是否有查询到数据
        if(list!=null&&list.size()>0){
            //如果查询到了表示用户名不可用，不能注册
            result.setData(false);
        }else {
            //如果没有查询到，则表示用户名可用，可以注册
            result.setData(true);
        }
        return result;
    }

    @Override
    public EgoResult saveUserTbService(TbUser tbUser) {
        EgoResult result = new EgoResult();
        try {
            Date date = new Date();
            tbUser.setCreated(date);
            tbUser.setUpdated(date);
            tbUserMapper.insert(tbUser);
            //如果没有出异常，注册成功，则设置状态为200
            result.setStatus(200);
            result.setMsg("注册成功");
        } catch (Exception e) {
            result.setStatus(400);
            result.setMsg("注册失败，请稍后再试");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbUser selectUserByUserName(String uname) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(uname);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        //虽然返回的是一个list，但是用户名查询出来必须是唯一的
        if(list!=null&&list.size()==1){
            return list.get(0);
        }
        return null;
    }
}
