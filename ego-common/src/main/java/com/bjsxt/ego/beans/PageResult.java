package com.bjsxt.ego.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 封装MyBatis插件：datagrid控件需要的数据类型
 * 需要从后台接收json格式的对象，这个对象中封装了两部分的数据
 * 一部分是数据集合，另外一部分是查询到的商品总数
 * @author lvyelanshan
 * @create 2019-10-18 8:47
 */
//将来是通过RPC远程调用的方式调用ego中的服务，获取相应的数据，
// 也就是说将来的消费者项目需要调用这里的服务，将这个数据返回给消费者
// 所以这里的javabean对象就必须进行序列化
public class PageResult<T> implements Serializable {

    //需要一个查询数据的集合，必须叫rows!
    private List<T> rows ;
    //还需要一个查询的商品总数,必须叫total！
    private Long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
