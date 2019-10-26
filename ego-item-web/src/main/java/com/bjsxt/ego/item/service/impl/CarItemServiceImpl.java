package com.bjsxt.ego.item.service.impl;

import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.item.dao.CarItemDao;
import com.bjsxt.ego.item.entity.CarItem;
import com.bjsxt.ego.item.service.CarItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-25 19:37
 */
@Service
public class CarItemServiceImpl implements CarItemService{
    //注入dao对象
    @Autowired
    private CarItemDao carItemDao;
    //注入远程服务对象，因为需要通过远程服务对象获取到商品的对象
    @Autowired(required = false)
    private ItemService itemServiceProxy;
    @Override
    public void addItemToCarService(Long itemid, Long uid) {
        Map<String,String> carMap=null;
        //每次增加相同的商品只需要数量上++，
        //并不需要每次都创建一个购物车对象！
        //所以这里需要进行判断
        CarItem carItem = null;

        //获得商品对象
        TbItem tbItem = itemServiceProxy.loadTbItemById(itemid);


        //判断用户是否第一次购物，或者是判断是否已经存在carMap
        carMap = carItemDao.loadCarMap(String.valueOf(uid));
        System.out.println("carMap---->"+carMap);
        if (carMap==null){ //如果是空的表示是第一次购物，上面查询不到任何商品信息
            //创建Map集合对象
            carMap = new HashMap<>();
        }

        //判断itemId对象的商品是否存在一个购物车对象（一种商品对应一个购物车对象）
        String carItemStr = carItemDao.loadCarItem(String.valueOf(uid), String.valueOf(itemid));
        if(StringUtils.isEmpty(carItemStr)){
            //如果没有查询到对应商品的购物车对象
            //创建一个购物车对象
            carItem = new CarItem();
            //将商品信息放入到购物车
            carItem.setItem(tbItem);
            carItem.setNum(1);
        }else {
            //将购物车对象转化为实体类对象
            carItem=JsonUtils.jsonToPojo(carItemStr,CarItem.class);
            carItem.setNum(carItem.getNum()+1);//修改购物车数量
        }


        //将购物车对象转化为json字符串
        String jsonStr = JsonUtils.objectToJson(carItem);

        //将购物车对象放入map集合
        carMap.put(String.valueOf(itemid),jsonStr);
        //将carMap集合保存到Redis数据库
        System.out.println("保存到Redis数据库中的数据："+String.valueOf(itemid)+"--"+jsonStr);
        System.out.println("用户UID："+uid);
        carItemDao.addCarMap(String.valueOf(uid),carMap);
    }

    @Override
    public Map<Long, CarItem> loadCarItemListService(Long uid) {

        Map<Long,CarItem> carItemMap = new HashMap<>();
        //获得某个用户的购物车列表
        Map<String, String> carMap = carItemDao.loadCarMap(String.valueOf(uid));
        if (!StringUtils.isEmpty(carMap)){//如果不是空的才遍历
            //循环遍历map集合
            for(Map.Entry<String,String> e:carMap.entrySet()){
                String key = e.getKey();
                String value = e.getValue();
                //将value字符串转化为CarItem对象
                CarItem carItem = JsonUtils.jsonToPojo(value,CarItem.class);

                //键是商品id，值是购物车对象
                carItemMap.put(Long.parseLong(key),carItem);
            }
        }

        return carItemMap;
    }

    @Override
    public String updateCarItemNumService(Long itemid, Long uid,Integer num) {

        try {
            //获得需要修改的购物车对象
            String carItemStr = carItemDao.loadCarItem(String.valueOf(uid), String.valueOf(itemid));
            //将carItemStr转化为carItem对象
            CarItem carItem = JsonUtils.jsonToPojo(carItemStr, CarItem.class);
            //修改购物车数量
            carItem.setNum(num);

            //将修改后的carItem(购物车)对象数据更新到Redis数据库
            carItemDao.updateCarMapNum(String.valueOf(uid),String.valueOf(itemid),
                    JsonUtils.objectToJson(carItem));

            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCarItemService(Long itemid, Long uid) {
        carItemDao.deleteCarMapItem(String.valueOf(uid),
                String.valueOf(itemid));
    }

    @Override
    public void deleteCarItemAllService(Long uid) {
        carItemDao.deleteCarMapAll(String.valueOf(uid));
    }
}
