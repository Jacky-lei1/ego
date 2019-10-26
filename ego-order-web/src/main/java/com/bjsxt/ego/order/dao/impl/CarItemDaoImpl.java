package com.bjsxt.ego.order.dao.impl;

import com.bjsxt.ego.order.dao.CarItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-26 15:37
 */
@Repository
public class CarItemDaoImpl implements CarItemDao {

    //注入JedisCluster对象
    @Autowired
    private JedisCluster cluster;
    @Override
    public Map<String, String> locadCarItemMap(String uid) {
        return cluster.hgetAll(uid);
    }

    @Override
    public void deleteCarItemMap(String uid) {
        cluster.del(uid);
    }
}
