package com.wxj.bean.base;

import java.util.Date;

/**
 *
 * Created by WangXiaojian on 2018/3/15 0015.
 *
 */
public class SimilarQuestionRelation {

    private Integer id; //相似问题关系ID

    private Integer companyId;//公司ID

    private Integer standardQuestionId; //标准问题ID

    private Integer similarQuestionId; //相似问题标题

    private String title; //相似问题标题

    private Integer is_del;//是否删除

    private Date createTime;//创建时间

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

    public Integer getStandardQuestionId() {
        return standardQuestionId;
    }

    public void setStandardQuestionId(Integer standradQuestionId) {
        this.standardQuestionId = standradQuestionId;
    }

    public Integer getSimilarQuestionId() {
        return similarQuestionId;
    }

    public void setSimilarQuestionId(Integer similarQuestionId) {
        this.similarQuestionId = similarQuestionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }
}
