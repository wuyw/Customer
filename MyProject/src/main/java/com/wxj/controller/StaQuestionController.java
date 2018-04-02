package com.wxj.controller;


import com.wxj.bean.ResponseBean;
import com.wxj.bean.dto.StandardQuestionDto;
import com.wxj.service.StandardQuestionService;
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
        return standardQuestionService.insertStaQuestion(standardQuestionDto);
    }

}
