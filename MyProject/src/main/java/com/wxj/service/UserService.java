package com.wxj.service;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.User;

public interface UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getUserByUserNameAndDomain(String username,String domain);

    /**
     * 登录
     *
     * @param loginUser
     * @return
     */
    ResponseBean toLogin(LoginUser loginUser);

    /**
     * 获取用户信息--demo
     *
     * @param userId
     * @return
     */
    ResponseBean getUserInfo(String userId);
}
