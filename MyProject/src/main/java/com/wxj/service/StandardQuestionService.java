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

    /**
     * 分页获取问题列表
     * @param companyId
     * @param page
     * @param perPage
     * @return
     */
    ResponseBean getStaQuestionList(Integer companyId,Integer page,Integer perPage);

    /**
     * 删除问题
     * @param ids
     * @return
     */
    ResponseBean delStaQuestion(Integer[] ids,Integer companyId);

    /**
     * 模糊查询
     * @param companyId
     * @param page
     * @param perPage
     * @return
     */
    ResponseBean getStaQuestionListByKeywords(String keywords,Integer companyId,Integer page,Integer perPage);

}
