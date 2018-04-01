package com.wxj.service.impl;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.KnowledgePoint;
import com.wxj.bean.base.User;
import com.wxj.mapper.KnowledgeMapper;
import com.wxj.mapper.UserMapper;
import com.wxj.service.CompanyService;
import com.wxj.service.KnowledgeService;
import com.wxj.service.UserService;
import com.wxj.shiro.StatelessAuthenticationToken;
import com.wxj.util.JsonWebTokenUtil;
import com.wxj.util.LogUtil;
import com.wxj.util.SecurityUtils;
import com.wxj.util.ValidateUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    private Logger logger = Logger.getLogger(CompanyService.class);

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    LogUtil logUtil;

    @Autowired
    KnowledgeMapper knowledgeMapper;

    /**
     * 新增知识点
     * @param knowledgePoint
     * @return
     */
    public ResponseBean insertKnowledge(KnowledgePoint knowledgePoint,Integer companyId){

        ResponseBean responseBean = new ResponseBean();
        //判断参数是否为空
        if(!knowledgePoint.isValid()){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
        }
        //执行插入操作
        int i = knowledgeMapper.insertKnowledge(knowledgePoint,companyId);
        if ( i <= 0){
            responseBean.setCode(ResponseBean.CODE_NO_RESULT);
        } else {
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        }
        return responseBean;
    }

    /**
     * 更新知识点
     * @param knowledgePoint
     * @return
     */
    public ResponseBean updateKnowledge(KnowledgePoint knowledgePoint){
        ResponseBean responseBean = new ResponseBean();
        //判断参数是否为空
        if(!knowledgePoint.isValid()){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
        }
        //执行更新操作
        int i = knowledgeMapper.updateKnowledge(knowledgePoint);
        if ( i <= 0){
            responseBean.setCode(ResponseBean.CODE_FAIL);
        } else {
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        }
        return responseBean;
    }

    /**
     * 知识点列表
     * @param companyId
     * @param page
     * @param perPage
     * @return
     */
    public ResponseBean getPointList(Integer companyId,Integer page,Integer perPage){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数是否为空
        if (companyId == null || page == null || perPage == null) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        int start = (page-1)*perPage;
        int end = perPage*page;
        //执行查询操作
        List<KnowledgePoint> pointList = knowledgeMapper.getPointList(companyId,start,end);
        if (pointList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_NOAUTH);
            return responseBean;
        }
        //获取知识点总数
        int numPage = knowledgeMapper.getPointCount(companyId);
        result.put("numPage",numPage);
        result.put("pointList",pointList);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }

    /**
     * 模糊查询
     * @param knowledgePoint
     * @param page
     * @param perPage
     * @return
     */
    public ResponseBean getPointListByParams(KnowledgePoint knowledgePoint,Integer page,Integer perPage){
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数是否为空
        if (!knowledgePoint.isValid()) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        int start = (page-1)*perPage;
        int end = perPage*page;
        //执行查询操作
        List<KnowledgePoint> pointList = knowledgeMapper.getPointListByParams(knowledgePoint,start,end);
        if (pointList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_NOAUTH);
            return responseBean;
        }
        //获取知识点总数
        int numPage = knowledgeMapper.getPointCountByParams(knowledgePoint.getCompanyId());
        result.put("numPage",numPage);
        result.put("pointList",pointList);
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        responseBean.setResult(result);
        return responseBean;
    }


}
