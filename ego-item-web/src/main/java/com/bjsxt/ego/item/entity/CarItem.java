package com.bjsxt.ego.item.entity;

import com.bjsxt.ego.rpc.pojo.TbItem;

import java.io.Serializable;

/**
 * 购物车类
 * @author lvyelanshan
 * @create 2019-10-25 19:24
 */
public class CarItem implements Serializable {

    private TbItem item;//购买的商品对象
    private Integer num;//购买的商品数量

    public TbItem getItem() {
        return item;
    }

    public void setItem(TbItem item) {
        this.item = item;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
