package com.wxj.bean.base;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
public class AssociatedQuestionRelation {

    private Integer id; //关联问题ID

    private Integer standardQuestionId; //标准问题ID

    private Integer associatedQuestionId; //关联问题ID

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
}
