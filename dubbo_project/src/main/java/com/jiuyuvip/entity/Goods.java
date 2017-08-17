package com.jiuyuvip.entity;

import java.io.Serializable;

public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer version=0;
    private Integer goods_id;
    private String goods_name;
    private String image_url;
    private Integer stock_number;

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }


    public  Integer getVersion() {
        return version;
    }

    public  void setVersion(Integer version) {
        this.version = version;
    }

    public int getStock_number() {

        return stock_number;
    }

    public void setStock_number(Integer stock_number) {
        this.stock_number = stock_number;
    }

    public String getImage_url() {

        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getGoods_name() {

        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
