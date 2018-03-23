package com.wxj.bean.base;

/**
 *
 * Created by WangXiaojian on 2018/3/15 0015.
 *
 */
public class questionStatistics {

    private Integer id; //问题统计ID

    private Integer questionId; //问题ID

    private Integer goodNum; //好评数

    private Integer badNum; //差评数

    private Integer solvedNum; //已解决数

    private Integer matchedNum; //匹配数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    public Integer getBadNum() {
        return badNum;
    }

    public void setBadNum(Integer badNum) {
        this.badNum = badNum;
    }

    public Integer getSolvedNum() {
        return solvedNum;
    }

    public void setSolvedNum(Integer solvedNum) {
        this.solvedNum = solvedNum;
    }

    public Integer getMatchedNum() {
        return matchedNum;
    }

    public void setMatchedNum(Integer matchedNum) {
        this.matchedNum = matchedNum;
    }
}
