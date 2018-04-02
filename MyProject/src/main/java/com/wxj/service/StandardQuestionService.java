package com.wxj.service;


import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.StandardQuestion;
import com.wxj.bean.dto.StandardQuestionDto;

public interface StandardQuestionService {

    /**
     * 新增问题
     * @param standardQuestionDto
     * @return
     */
    ResponseBean insertStaQuestion(StandardQuestionDto standardQuestionDto);
}
