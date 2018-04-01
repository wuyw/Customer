package com.wxj.util;

import com.wxj.bean.base.Company;
import com.wxj.bean.base.User;
import com.wxj.bean.shiro.ShiroUser;
import org.apache.shiro.subject.Subject;


public abstract class SecurityUtils {
    public static User getLoginUser() {
        return getShiroUser().getUser();
    }

    public static ShiroUser getShiroUser() {
        Subject subject = getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        return shiroUser;
    }

    public static Subject getSubject() {
        return org.apache.shiro.SecurityUtils.getSubject();
    }

}