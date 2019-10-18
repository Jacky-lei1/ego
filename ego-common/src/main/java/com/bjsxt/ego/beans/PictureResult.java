package com.bjsxt.ego.beans;

/**
 * 封装上传图片之后的返回结果
 * @author lvyelanshan
 * @create 2019-10-18 20:59
 */
public class PictureResult {
    //状态：1失败，0成功
    private Integer error;
    //上传图片后，图片在服务器的URL
    private String url;
    //响应到客户端的提示内容
    private String message;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
