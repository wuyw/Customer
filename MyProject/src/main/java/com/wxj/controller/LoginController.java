package com.wxj.controller;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.User;
//import com.sluan.config.rabbitmq1.CallBackSender;
import com.wxj.log.Log;
import com.wxj.log.LogAspect;
import com.wxj.service.UserService;
import com.wxj.util.LogUtil;
import com.wxj.util.RedisUtil;
import com.wxj.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LogUtil logUtil;

//    @Autowired
//    CallBackSender callBackSender;

  /*  @Autowired
    RabbitMqSender1 rabbitMqSender1;

    @Autowired
    RabbitMqSender2 rabbitMqSender2;*/

    /**
     * 用户登录
     *
     * @param loginUser loginUser 登录用户信息
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
    @ResponseBody
    @Log(message = "登录了系统")
    public ResponseBean login(@RequestBody LoginUser loginUser) {
        //测试发送数据
//        for(int i=0;i<1000;i++){
//            rabbitMqSender1.sendRabbitmqFanout();
//            rabbitMqSender2.sendRabbitmqFanout();
//        }
        return userService.toLogin(loginUser);
    }

    /**
     * 用户退出登录
     *
     * @param token
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user/login/out")
    @ResponseBody
    public ResponseBean loginOut(@RequestHeader(value = "Authorization") String token, HttpServletRequest request) {
        ResponseBean response = new ResponseBean();
        User user = SecurityUtils.getLoginUser();
        redisUtil.remove(RedisUtil.REDIS_KEY + "auth_permission:" + token);
        redisUtil.remove(RedisUtil.REDIS_KEY + "auth_info:" + token);
        response.setCode(ResponseBean.CODE_SUCCESS);
        String ip = LogAspect.getIpAddress(request);
        logUtil.addLog(ip,user.getAccount() + "用户退出登录", user.getAccount());
        return response;
    }

}
