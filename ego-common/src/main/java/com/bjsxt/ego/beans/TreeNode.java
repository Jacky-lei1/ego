package com.bjsxt.ego.beans;

import java.io.Serializable;

/**封装树控件需要的数据模型
 * @author lvyelanshan
 * @create 2019-10-18 16:59
 */
public class TreeNode implements Serializable {

    private Long id;
    private String text;
    private String state; //这里的状态必须为state，响应回去EasyUI控件解析约定的state为父节点属性，statue为子节点属性

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
