package com.example.sqlite.model;

import java.io.Serializable;

public class Work implements Serializable {
    private int id;
    private String name, desc, dateline, status, collab;

    public Work() {
    }

    public Work(String name, String desc, String dateline, String status, String collab) {
        this.name = name;
        this.desc = desc;
        this.dateline = dateline;
        this.status = status;
        this.collab = collab;
    }

    public Work(int id, String name, String desc, String dateline, String status, String collab) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.dateline = dateline;
        this.status = status;
        this.collab = collab;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCollab() {
        return collab;
    }

    public void setCollab(String collab) {
        this.collab = collab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
