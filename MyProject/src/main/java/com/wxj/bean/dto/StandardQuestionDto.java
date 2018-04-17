package com.wxj.bean.dto;

import com.wxj.bean.base.StandardQuestion;

/**
 *
 * Created by WangXiaojian on 2018/3/14.
 *
 */
public class StandardQuestionDto extends StandardQuestion {

    private String[] simQuestion;

    private Integer[] assQuestion;

    public String[] getSimQuestion() {
        return simQuestion;
    }

    public void setSimQuestion(String[] simQuestion) {
        this.simQuestion = simQuestion;
    }

    public Integer[] getAssQuestion() {
        return assQuestion;
    }

    public void setAssQuestion(Integer[] assQuestion) {
        this.assQuestion = assQuestion;
    }
}
