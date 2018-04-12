package com.wxj.controller;


import com.wxj.bean.Request.RequestObject;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.dto.StandardQuestionDto;
import com.wxj.service.StandardQuestionService;
import com.wxj.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaQuestionController {

    @Autowired
    StandardQuestionService standardQuestionService;

    /**
     * 新增问题
     * @param standardQuestionDto
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/question/add")
    @ResponseBody
    public ResponseBean insertQuestion(@RequestBody StandardQuestionDto standardQuestionDto){
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        standardQuestionDto.setCompanyId(companyId);
        return standardQuestionService.insertStaQuestion(standardQuestionDto);
    }

    /**
     * 分页获取问题列表
     * @param requestObject
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/question/list")
    @ResponseBody
    public ResponseBean getStaQuestionList(@RequestBody RequestObject requestObject){
        Integer page = requestObject.getPage();
        Integer perPgae = requestObject.getPerPage();
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        return standardQuestionService.getStaQuestionList(companyId,page,perPgae);
    }
}
