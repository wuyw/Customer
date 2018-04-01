package com.wxj.bean.base;

import com.wxj.util.ValidateUtil;

import java.util.Date;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
public class KnowledgePoint {

    private Integer id; //知识点ID

    private Integer companyId;//所属企业ID

    private String title; //知识点标题

    private String content; //知识点内容

    private Date createTime; //创建时间

    private Integer status; //是否永久有效

    private Date startTime; //开始时间

    private Date endTime; //结束时间

    private Integer isDel;//是否删除

    private Date editTime;//更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public boolean isValid(){
        return !ValidateUtil.isEmpty(title) && !ValidateUtil.isEmpty(content) && !ValidateUtil.isEmpty(status);
    }

}
