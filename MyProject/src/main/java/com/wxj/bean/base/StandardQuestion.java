package com.wxj.bean.base;

import com.wxj.util.ValidateUtil;

import java.util.Date;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
public class StandardQuestion {

    private Integer id; //问题ID

    private Integer companyId;//公司ID

    private Integer knowledgePointId; //知识点ID

    private String title; //问题标题

    private String answer; //问题答案

    private Integer status; //是否长久有效

    private Date createTime; //创建时间

    private Date startTime; //开始时间

    private Date endTime; //结束时间

    private Integer isDel;//是否删除

    private Date delTime;//删除时间

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

    public Integer getKnowledgePointId() {
        return knowledgePointId;
    }

    public void setKnowledgePointId(Integer knowledgePointId) {
        this.knowledgePointId = knowledgePointId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public boolean isValid() {
        return !ValidateUtil.isEmpty(title) && !ValidateUtil.isEmpty(answer) && !ValidateUtil.isEmpty(status);
    }
}
