package com.wxj.service;


import com.sun.org.apache.regexp.internal.RE;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.KnowledgePoint;
import com.wxj.bean.dto.KnowledgePointDto;

public interface KnowledgeService {

    /**
     * 新增知识点
     * @param knowledgePoint
     * @return
     */
    ResponseBean insertKnowledge(KnowledgePoint knowledgePoint,Integer companyId);

    /**
     * 更新知识点
     * @return
     */
    ResponseBean updateKnowledge(KnowledgePoint knowledgePoint);

    /**
     * 列表查询知识点
     * @param companyId
     * @param page
     * @param perPage
     * @return
     */
    ResponseBean getPointList(Integer companyId,Integer page,Integer perPage);

    /**
     * 模糊查询
     * @param knowledgePoint
     * @param page
     * @param perPage
     * @return
     */
    ResponseBean getPointListByParams(KnowledgePoint knowledgePoint,Integer page,Integer perPage);

    /**
     * 通过ID查询知识点
     * @param id
     * @return
     */
    ResponseBean getPointById(Integer id);

    /**
     * 删除知识点
     * @param ids
     * @return
     */
    ResponseBean delPoint(String ids);
}
