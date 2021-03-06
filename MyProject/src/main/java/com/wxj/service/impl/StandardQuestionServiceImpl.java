package com.wxj.service.impl;


import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.AssociatedQuestionRelation;
import com.wxj.bean.base.KnowledgePoint;
import com.wxj.bean.base.SimilarQuestionRelation;
import com.wxj.bean.base.StandardQuestion;
import com.wxj.bean.dto.StandardQuestionDto;
import com.wxj.mapper.AssQuestionMapper;
import com.wxj.mapper.KnowledgeMapper;
import com.wxj.mapper.SimilarQuestionMapper;
import com.wxj.mapper.StandardQuestionMapper;
import com.wxj.service.StandardQuestionService;
import com.wxj.util.LogUtil;
import com.wxj.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class StandardQuestionServiceImpl implements StandardQuestionService {

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    LogUtil logUtil;

    @Autowired
    StandardQuestionMapper standardQuestionMapper;

    @Autowired
    SimilarQuestionMapper similarQuestionMapper;

    @Autowired
    KnowledgeMapper knowledgeMapper;

    @Autowired
    AssQuestionMapper assQuestionMapper;

    /**
     * 新增问题
     * @param standardQuestionDto
     * @return
     */
    public ResponseBean insertStaQuestion(StandardQuestionDto standardQuestionDto){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数有效性
        if (!standardQuestionDto.isValid()){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        //查询此标题的问题数据库是否已经存在
        StandardQuestion standardQuestion = standardQuestionMapper.
                getStaQuestionByTitle(standardQuestionDto.getTitle(),standardQuestionDto.getCompanyId());
        SimilarQuestionRelation similarQuestionRelation = similarQuestionMapper.
                getSimQuestionByTitle(standardQuestionDto.getTitle(),standardQuestionDto.getCompanyId());
        if(!ValidateUtil.isEmpty(standardQuestion) || !ValidateUtil.isEmpty(standardQuestion)){
            result.put("title",standardQuestionDto.getTitle());
            responseBean.setCode(ResponseBean.CODE_Value_EXIST);
            responseBean.setResult(result);
            return responseBean;
        }
        //判断相似问题是否存在
        String [] simQuestion = standardQuestionDto.getSimQuestion();
        if(simQuestion != null && simQuestion.length != 0){
            for(int i = 0;i<=simQuestion.length;i++){
                SimilarQuestionRelation similarQuestionRelation1 = similarQuestionMapper.
                        getSimQuestionByTitle(standardQuestionDto.getTitle(),standardQuestionDto.getCompanyId());
                if(!ValidateUtil.isEmpty(similarQuestionRelation1)){
                    result.put("title",simQuestion[i]);
                    responseBean.setCode(ResponseBean.CODE_Value_EXIST);
                    responseBean.setResult(result);
                    return responseBean;
                }
            }

        }

        //添加问题
        int i = standardQuestionMapper.insertStaQuestion(standardQuestionDto);
        if (i<=0){
            responseBean.setCode(ResponseBean.CODE_FAIL);
            return responseBean;
        }
        //添加成功后获取问题ID
        StandardQuestion standardQuestion1 = standardQuestionMapper.getStaQuestionByTitle(standardQuestionDto.getTitle(),standardQuestionDto.getCompanyId());

        //添加相似问题
        if(simQuestion != null &&simQuestion.length != 0){
            for (int k = 0;k<simQuestion.length;k++){
                SimilarQuestionRelation similarQuestionRelation1 = new SimilarQuestionRelation();
                similarQuestionRelation1.setCompanyId(standardQuestionDto.getCompanyId());
                similarQuestionRelation1.setTitle(simQuestion[k]);
                similarQuestionRelation1.setStandardQuestionId(standardQuestion1.getId());
                similarQuestionMapper.insertSimQuestion(similarQuestionRelation1);
            }
        }
        //添加关联问题
        Integer[]  assQuestionIds = standardQuestionDto.getAssQuestion();
        if (assQuestionIds != null && assQuestionIds.length != 0) {
            AssociatedQuestionRelation associatedQuestionRelation = new AssociatedQuestionRelation();
            associatedQuestionRelation.setCompanyId(standardQuestion.getCompanyId());
            associatedQuestionRelation.setAssociatedQuestionId(assQuestionIds[i]);
            associatedQuestionRelation.setStandardQuestionId(standardQuestion1.getId());
            assQuestionMapper.insertAssQuestion(associatedQuestionRelation);
        }
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        return responseBean;
    }


    /**
     * 分页获取问题列表
     * @param companyId
     * @param page
     * @param perPage
     * @return
     */
    public ResponseBean getStaQuestionList(Integer companyId,Integer page,Integer perPage){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数有效性
        if (companyId ==  null){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        Integer start = (page-1)*perPage;
        Integer end = perPage*page;
        //分页查询
        List<StandardQuestion> staQuestionList = standardQuestionMapper.getStaQuestionList(companyId,start,end);
        for( int i = 0;i<staQuestionList.size();i++){
            Integer pointId = staQuestionList.get(i).getKnowledgePointId();

            if(pointId != null){
                //通过ID去查知识点
                KnowledgePoint knowledgePoint = knowledgeMapper.getPointById(pointId);
                staQuestionList.get(i).setPoint(knowledgePoint.getTitle());
            }else{
                staQuestionList.get(i).setPoint(null);
            }

        }


        if (staQuestionList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_NO_RESULT);
            return responseBean;
        }
        int total = standardQuestionMapper.getQuestionCount(companyId);
        result.put("total",total);
        result.put("staQuestionList",staQuestionList);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }

    /**
     * 删除问题
     * @param ids
     * @return
     */
    @Override
    public ResponseBean delStaQuestion(Integer[] ids,Integer companyId) {
        ResponseBean responseBean = new ResponseBean();
        //验证参数有效性
        if (ids.length == 0) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        for (int i = 0; i < ids.length; i++) {
            //删除问题
             int j = standardQuestionMapper.delStaQuestion(ids[i],companyId);
            if (j < 0){
                responseBean.setCode(ResponseBean.CODE_FAIL);
                return responseBean;
            }
            //删除问题相似问题
            int k = similarQuestionMapper.delSimQuestion(ids[i],companyId);
            if (k < 0){
                responseBean.setCode(ResponseBean.CODE_FAIL);
                return responseBean;
            }
            //删除问题关联问题

        }
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        return responseBean;
    }

    /**
     * 模糊查询
     * @param companyId
     * @param page
     * @param perPage
     * @return
     */
    public ResponseBean getStaQuestionListByKeywords(String keywords,Integer companyId,Integer page,Integer perPage){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数有效性
        if (companyId ==  null){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        Integer start = (page-1)*perPage;
        Integer end = perPage*page;
        //分页查询
        List<StandardQuestion> staQuestionList = standardQuestionMapper.getStaQuestionListByKeywords(keywords,companyId,start,end);
        for( int i = 0;i<staQuestionList.size();i++){
            Integer pointId = staQuestionList.get(i).getKnowledgePointId();
            if(pointId == null){
                staQuestionList.get(i).setPoint(null);
            }else{
                //通过ID去查知识点
                KnowledgePoint knowledgePoint = knowledgeMapper.getPointById(pointId);
                staQuestionList.get(i).setPoint(knowledgePoint.getTitle());
            }

        }


        if (staQuestionList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_NO_RESULT);
            return responseBean;
        }
        int total = standardQuestionMapper.getQuestionCountByKeywords(keywords,companyId);
        result.put("total",total);
        result.put("staQuestionList",staQuestionList);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }
}
