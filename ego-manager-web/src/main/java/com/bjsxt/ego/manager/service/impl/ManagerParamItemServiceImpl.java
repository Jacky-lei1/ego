package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerParamItemService;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.service.ParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lvyelanshan
 * @create 2019-10-20 20:42
 */
@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {

    @Autowired(required = false)
    private ParamItemService paramItemServiceProxy;

    @Override
    public EgoResult loadParamItemService(Long itemid) {

        EgoResult result = null;
        try {
            /**
             * 将获取到的结果封装到EgoResult中,
             * 将查询到的结果封装到EgoResult中的data属性中
             * 将状态设为200，响应回前端判断使用
             */
            //调用远程服务
            TbItemParamItem tbItemParamItem = paramItemServiceProxy.loadTbItemParamService(itemid);
            //如果查询没有问题则新建一个EgoResult对象，设置状态为200
            result = new EgoResult();
            result.setStatus(200);
            //将查询到的数据封装进去
            result.setData(tbItemParamItem);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
