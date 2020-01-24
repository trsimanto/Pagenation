package com.towhid.pagenation.network.model;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private Double value;

    @SerializedName("tr")
    private Boolean tr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        if (name==null)
            return "error name";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getTr() {
        return tr;
    }

    public void setTr(Boolean tr) {
        this.tr = tr;
    }
}
