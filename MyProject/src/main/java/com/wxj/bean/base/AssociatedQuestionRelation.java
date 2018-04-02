package com.wxj.bean.base;

import java.util.Date;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
public class AssociatedQuestionRelation {

    private Integer id; //关联问题ID

    private Integer standardQuestionId; //标准问题ID

    private Integer associatedQuestionId; //关联问题ID

    private Integer companyId;//公司ID

    private Integer is_del;

    private Date createTime;

    private Date delTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return standardQuestionId;
    }

    public void setQuestionId(Integer standardQuestionId) {
        this.standardQuestionId = standardQuestionId;
    }

    public Integer getAssociatedQuestionId() {
        return associatedQuestionId;
    }

    public void setAssociatedQuestionId(Integer associatedQuestionId) {
        this.associatedQuestionId = associatedQuestionId;
    }

    public Integer getStandardQuestionId() {
        return standardQuestionId;
    }

    public void setStandardQuestionId(Integer standardQuestionId) {
        this.standardQuestionId = standardQuestionId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
