package com.bjsxt.ego.item.dao.impl;

import com.bjsxt.ego.item.dao.CarItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-25 19:31
 */
@Repository
public class CarItemDaoImpl implements CarItemDao {

    //要访问Redis数据库，就需要注入一个对象
    @Autowired
    private JedisCluster cluster;

    @Override
    public void addCarMap(String uid, Map<String, String> carMap) {
        //将传入的购物车对象的集合保存到Redis数据库中
        System.out.println("传入Redis数据库的值："+"用户ID："+uid+"-----"+"商品集合："+carMap);
        String hmset = cluster.hmset(uid, carMap);
        System.out.println("添加情况："+hmset);
    }

    @Override
    public Map<String, String> loadCarMap(String uid) {
        //根据用户UID查询Redis数据库中该用户对应的购物车对象和商品对应的map集合
        System.out.println("cluster.hgetAll(uid)---->"+cluster.hgetAll(uid));
        return cluster.hgetAll(uid);
    }

    @Override
    public String loadCarItem(String uid, String itemid) {
        //根据用户UID和商品ID得到购物车对象
        return cluster.hget(uid,itemid);
    }

    @Override
    public void updateCarMapNum(String uid, String itemid, String carItemStr) {
        cluster.hset(uid,itemid,carItemStr);
    }

    @Override
    public void deleteCarMapItem(String uid, String itemid) {
        cluster.hdel(uid,itemid);
    }

    @Override
    public void deleteCarMapAll(String uid) {
        cluster.del(uid);
    }
}
