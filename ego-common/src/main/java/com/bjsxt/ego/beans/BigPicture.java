package com.bjsxt.ego.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**封装响应到前台的大广告数据格式
 * @author lvyelanshan
 * @create 2019-10-23 9:49
 */
public class BigPicture {
    /*默认情况下这个对象被序列化成json串后，它是按照属性名来作为参数的key*/
    @JsonProperty("srcB")
    private String srcb;
    @JsonProperty("height")
    private Integer height=240;
    @JsonProperty("alt")
    private String alt;
    @JsonProperty("width")
    private Integer width=670;
    @JsonProperty("src")
    private String src;
    @JsonProperty("widthB")
    private Integer widthb=550;
    @JsonProperty("href")
    private String href;
    @JsonProperty("heightB")
    private Integer heightb=240;

    public BigPicture(String srcb, Integer height, String alt, Integer width, String src, Integer widthb, String href, Integer heightb) {
        this.srcb = srcb;
        this.height = height;
        this.alt = alt;
        this.width = width;
        this.src = src;
        this.widthb = widthb;
        this.href = href;
        this.heightb = heightb;
    }

    public BigPicture() {
    }

    public String getSrcb() {
        return srcb;
    }

    public void setSrcb(String srcb) {
        this.srcb = srcb;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getWidthb() {
        return widthb;
    }

    public void setWidthb(Integer widthb) {
        this.widthb = widthb;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getHeightb() {
        return heightb;
    }

    public void setHeightb(Integer heightb) {
        this.heightb = heightb;
    }

    @Override
    public String toString() {
        return "BigPicture{" +
                "srcb='" + srcb + '\'' +
                ", height=" + height +
                ", alt='" + alt + '\'' +
                ", width=" + width +
                ", src='" + src + '\'' +
                ", widthb=" + widthb +
                ", href='" + href + '\'' +
                ", heightb=" + heightb +
                '}';
    }
}
