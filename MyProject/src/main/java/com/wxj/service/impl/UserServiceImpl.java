package com.wxj.service.impl;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.User;
import com.wxj.mapper.UserMapper;
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
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserService.class);

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    LogUtil logUtil;

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUserNameAndDomain(String username,String domain) {
        return userMapper.getUserByUsernameAndDomain(username,domain);
    }

    @Override
    public ResponseBean toLogin(LoginUser loginUser) {
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        //判断参数是否为空
        if (!loginUser.isValid()) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        //记住密码
        if (ValidateUtil.isNullOrEmpty(loginUser.getRemember_me())) {
            loginUser.setRemember_me(false);
        }
        Subject user = SecurityUtils.getSubject();
        if (user.isAuthenticated()) {
            user.logout();
        }
        Integer expire = 120;
        if (loginUser.getRemember_me()) {
            expire = 43200;
        }
        String jwt = new JsonWebTokenUtil(jwtKey).getJWTString(loginUser.getAccount(),loginUser.getCompanyDomain(), expire);
        StatelessAuthenticationToken token = new StatelessAuthenticationToken(loginUser.getAccount(),loginUser.getCompanyDomain(), loginUser.getPassword(), jwt);
        try {
            user.login(token);
            result.put("token", jwt);
            responseBean.setResult(result);
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        } catch (Exception e) {
            logger.error("[login] err: " + e);
            responseBean.setCode(ResponseBean.CODE_NOAUTH);
            logger.info("[login] user is not validate");
        }
        return responseBean;
    }

    @Override
    public ResponseBean getUserInfo(String userId) {
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        User user = userMapper.getUserInfo(userId);
        result.put("user",user);
        responseBean.setResult(result);
        return responseBean;
    }

}
