package com.wxj.controller;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.Company;
import com.wxj.service.CompanyService;
import com.wxj.util.RedisUtil;
import com.wxj.util.SMSSendUtil;
import com.wxj.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 *
 * Created by WangXiaojian on 2018/3/14 0014.
 *
 */
@Controller
public class RegistController {

    @Autowired
    SMSSendUtil sMSSendUtil;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ValidateUtil validateUtil;

    @Autowired
    CompanyService userService;


    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/regist/sendCode")
    @ResponseBody
    public ResponseBean sendCode(String phone){
        ResponseBean responseBean = new ResponseBean();
        //手机号为空判断
        if (ValidateUtil.isEmpty(phone)){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        try {
            //随机生成四位字符
            String regCode = getCode();
            //发送验证码
            SendSmsResponse response= SMSSendUtil.sendSMS(phone,"SMS_129590297",regCode);
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());
            //将验证码保存在redis中，有效时间是5分钟
            redisUtil.set(phone,regCode,Long.valueOf(5*60));
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
            responseBean.setCode(ResponseBean.CODE_FAIL);
        }
        return responseBean;
    }

    /**
     * 验证企业信息
     * @param company
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/regist/validate")
    @ResponseBody
    public ResponseBean userValidate(@RequestBody Company company){
        return userService.companyValidate(company);
    }

    /**
     * 保存企业信息
     * @param company
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value = "/regist/saveCompany")
    @ResponseBody
    public ResponseBean saveUser(@RequestBody Company company){
        return userService.saveCompany(company);
    }



    /**
     * 随机生成四位字符，用于生成验证码
     * @return
     */
    public static String getCode(){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
