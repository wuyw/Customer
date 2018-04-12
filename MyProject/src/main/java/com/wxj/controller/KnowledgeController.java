package com.wxj.controller;


import com.wxj.bean.Request.RequestObject;
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
     * @param knowledgePoint
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/point/add")
    @ResponseBody
    ResponseBean insertKnowledge(@RequestBody KnowledgePoint knowledgePoint){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        return knowledgeService.insertKnowledge(knowledgePoint,companyId);
    }

    /**
     * 更新知识点
     * @param knowledgePointDto
     * @return
     */
    @RequestMapping(method = { RequestMethod.GET,RequestMethod.POST}, value = "/point/update")
    @ResponseBody
    ResponseBean updateKnowledge(@RequestBody KnowledgePointDto knowledgePointDto){
        return knowledgeService.updateKnowledge(knowledgePointDto);
    }

    /**
     * 分页查询
     * @param requestObject
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/point/pointList")
    @ResponseBody
    ResponseBean getPointList(@RequestBody RequestObject requestObject){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        Integer page = requestObject.getPage();
        Integer perPage = requestObject.getPerPage();
        return knowledgeService.getPointList(companyId,page,perPage);
    }

    /**
     * 模糊查询
      * @param page
     * @param perPage
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/point/search")
    @ResponseBody
    ResponseBean getPointListByParams(@RequestBody KnowledgePoint knowledgePoint,@RequestHeader(value = "page") Integer page, @RequestHeader(value = "perPage") Integer perPage){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        knowledgePoint.setCompanyId(companyId);
        return knowledgeService.getPointListByParams(knowledgePoint,page,perPage);
    }

    /**
     * 通过ID查询知识点
     * @param knowledgePoint
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/point/info")
    @ResponseBody
    ResponseBean getPointById(@RequestBody KnowledgePoint knowledgePoint){
        Integer id = knowledgePoint.getId();
        return knowledgeService.getPointById(id);
    }


    /**
     * 删除知识点
     * @param ids
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/point/delete")
    @ResponseBody
    ResponseBean delPoint(@RequestParam String ids){
        return knowledgeService.delPoint(ids);
    }

}
