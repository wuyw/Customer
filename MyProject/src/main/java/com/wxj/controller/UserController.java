package com.wxj.controller;

import com.wxj.bean.ResponseBean;
import com.wxj.log.Log;
import com.wxj.service.UserService;
import com.wxj.util.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取用户信息 --demo
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/user/info")
    @ResponseBody
    @RequiresPermissions("User:select")
    @Log(message = "查询用户信息")
    public ResponseBean getUserInfo() {
        Integer userId = SecurityUtils.getLoginUser().getId();
        return userService.getUserInfo(String.valueOf(userId));
    }


}

