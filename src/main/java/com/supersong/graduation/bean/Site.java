package com.supersong.graduation.bean;

import java.util.Date;
import java.util.List;

public class Site {
    private String id;
    private String name;
    private String parentId;
    private Date createTime;
    private String remark;
    /**********************/

    private List<Site> child;

    /**********************/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Site> getChild() {
        return child;
    }

    public void setChild(List<Site> child) {
        this.child = child;
    }
}
