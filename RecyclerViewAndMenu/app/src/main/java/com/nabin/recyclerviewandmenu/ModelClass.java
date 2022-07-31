package com.nabin.recyclerviewandmenu;

public class ModelClass {
    private String name;
    private String time;
    private String desc;

    public ModelClass(String name, String time, String desc) {
        this.name = name;
        this.time = time;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
