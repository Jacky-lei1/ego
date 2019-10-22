package com.bjsxt.ego.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-22 21:34
 */
public class CatNode {

    //@JsonProperty:将指定的java对象转化成json格式的时候，对应的key
    @JsonProperty(value = "u")
    private String url;
    @JsonProperty(value = "n")
    private String name;
    @JsonProperty(value = "i")
    private List<?> list;


    public CatNode(String url, String name, List list) {
        this.url = url;
        this.name = name;
        this.list = list;
    }

    public CatNode() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CatNode{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
