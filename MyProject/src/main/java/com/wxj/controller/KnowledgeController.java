package com.wxj.controller;


import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.KnowledgePoint;
import com.wxj.bean.dto.KnowledgePointDto;
import com.wxj.service.KnowledgeService;
import com.wxj.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class KnowledgeController {

    @Autowired
    KnowledgeService knowledgeService;

    /**
     * 新增知识点
     * @param knowledgePointDto
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/point/add")
    @ResponseBody
    ResponseBean insertKnowledge(KnowledgePointDto knowledgePointDto){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        return knowledgeService.insertKnowledge(knowledgePointDto,companyId);
    }

    /**
     * 更新知识点
     * @param knowledgePointDto
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/point/update")
    @ResponseBody
    ResponseBean updateKnowledge(KnowledgePointDto knowledgePointDto){
        return knowledgeService.updateKnowledge(knowledgePointDto);
    }

    /**
     * 分页查询
     * @param page
     * @param perPage
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/point/pointList")
    @ResponseBody
    ResponseBean getPointList(@RequestHeader(value = "page") Integer page, @RequestHeader(value = "perPage") Integer perPage){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        return knowledgeService.getPointList(companyId,page,perPage);
    }

    /**
     * 模糊查询
      * @param page
     * @param perPage
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/point/search")
    @ResponseBody
    ResponseBean getPointListByParams(@RequestBody KnowledgePoint knowledgePoint,@RequestHeader(value = "page") Integer page, @RequestHeader(value = "perPage") Integer perPage){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        knowledgePoint.setCompanyId(companyId);
        return knowledgeService.getPointListByParams(knowledgePoint,page,perPage);
    }
}
