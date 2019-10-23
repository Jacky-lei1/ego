package com.bjsxt.ego.portal.service.impl;

import com.bjsxt.ego.beans.BigPicture;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.portal.service.PortalContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-23 10:19
 */
@Service
public class PortalContentServiceImpl implements PortalContentService {

    @Value("${CONTENT_PICTURE}")
    private String contentPictureKey;
    //注入JedisCluster对象
    @Autowired
    private JedisCluster cluster;

    @Autowired(required = false)
    private TbContentService tbContentServiceProxy;
    @Override
    public String loadContentListByCidService(Long cid) {

        //查询Redis数据库,获取键中的值
        String jsonStr = cluster.get(contentPictureKey);
        if (!StringUtils.isEmpty(jsonStr)){
            //如果查询到数据，jsonstr不为空则直接返回
            return jsonStr;
        }

        //调用远程服务
        List<TbContent> list =
                tbContentServiceProxy.loadTbContentListByCidService(cid);
        //封装符合前台数据格式的广告数据
        List<BigPicture> bigPictures = new ArrayList<BigPicture>();
        for(TbContent content:list){
            BigPicture pic = new BigPicture();
            pic.setSrcb(content.getPic());
            pic.setHeight(240);
            pic.setAlt(content.getTitle());
            pic.setWidth(670);
            pic.setSrc(content.getPic2());
            pic.setWidthb(550);
            pic.setHref(content.getUrl());
            pic.setHeightb(240);
            bigPictures.add(pic);
        }
        /**
         * 将符合前台数据规范的List集合序列化为json字符串
         */
        String json = JsonUtils.objectToJson(bigPictures);
        //将str保存到Redis缓存中
        cluster.set(contentPictureKey,json);
        /*设置缓存数据的生命周期，过了一天就会从数据库中再次查找数据*/
        cluster.expire(contentPictureKey,86400);
        return json;
    }
}
