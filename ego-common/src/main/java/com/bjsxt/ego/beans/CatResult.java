package com.bjsxt.ego.beans;

import java.util.List;

/**
 * 封装响应到前台的数据模型，将来被解析成json时能够作为键名
 * @author lvyelanshan
 * @create 2019-10-22 21:32
 */
public class CatResult {
    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
