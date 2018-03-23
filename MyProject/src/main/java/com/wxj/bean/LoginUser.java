package com.wxj.bean;

import com.wxj.util.ValidateUtil;

/**
 *
 * Created by WangXiaojian on 2018/3/15 0015.
 *
 */
public class LoginUser {

    private String account; //用户名

    private String password; //密码

    private String companyDomain; //企业域名

    private boolean remember_me = false; //记住密码

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyDomain() {
        return companyDomain;
    }

    public void setCompanyDomain(String companyDomain) {
        this.companyDomain = companyDomain;
    }

    public boolean getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(boolean remember_me) {
        this.remember_me = remember_me;
    }

    public boolean isValid() {
        return !ValidateUtil.isNullOrEmpty(this.account) && !ValidateUtil.isNullOrEmpty(this.password) && !ValidateUtil.isNullOrEmpty(this.companyDomain);
    }

}
