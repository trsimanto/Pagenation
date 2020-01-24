package com.towhid.pagenation.network.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PageInfo {

    @SerializedName("status")
    private String status;

    @SerializedName("length")
    private Integer length;

    @SerializedName("payload")
    private List<Info> info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }
}