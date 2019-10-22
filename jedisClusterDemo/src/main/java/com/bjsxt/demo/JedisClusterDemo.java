package com.bjsxt.demo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lvyelanshan
 * @create 2019-10-22 19:16
 */
public class JedisClusterDemo {
    public static void main(String[] args) {
        /**
         * 测试Redis集群操作
         */

        //创建HostAndPort对象
        HostAndPort node1 = new HostAndPort("192.168.43.11",6380);
        HostAndPort node2 = new HostAndPort("192.168.43.11",6381);
        HostAndPort node3 = new HostAndPort("192.168.43.11",6382);
        HostAndPort node4 = new HostAndPort("192.168.43.11",6383);
        HostAndPort node5 = new HostAndPort("192.168.43.11",6384);
        HostAndPort node6 = new HostAndPort("192.168.43.11",6385);

        //创建集合对象Set<HostAndPort>
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);

        //连接Redis集群
        JedisCluster cluster = new JedisCluster(nodes);

        cluster.set("name","test");

        String name = cluster.get("name");
        System.out.println("name="+name);
    }
}
