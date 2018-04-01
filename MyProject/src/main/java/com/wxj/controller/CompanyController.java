package com.wxj.controller;

import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.Company;
import com.wxj.log.Log;
import com.wxj.service.CompanyService;
import com.wxj.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyController {

    @Autowired
    CompanyService userService;

    /**
     * 获取企业信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/company/info")
    @ResponseBody
    @Log(message = "查询企业信息")
    public ResponseBean getUserInfo() {
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        return userService.getCompanyInfo(String.valueOf(companyId));
    }

    /**
     * 更新企业信息
     * @param company
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/company/update")
    @ResponseBody
    public ResponseBean updateUserInfo(@RequestBody Company company) {
        Integer companyId = SecurityUtils.getLoginUser().getCompanyId();
        company.setId(companyId);
        return userService.updateCompanyInfo(company);
    }
}

