package com.wxj.service;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.Company;
import org.springframework.web.bind.annotation.RequestBody;

public interface CompanyService {

    /**
     * 根据企业域获取企业信息
     *
     * @param domain
     * @return
     */
    Company getCompanyByDomain(String domain);


    /**
     * 获取用户信息--demo
     *
     * @param userId
     * @return
     */
    ResponseBean getCompanyInfo(String userId);

    /**
     * 保存用户信息
     * @param company
     * @return
     */
    ResponseBean saveCompany(@RequestBody Company company);

    /**
     * 用户重复性验证
     * @param company
     * @return
     */
    ResponseBean companyValidate(Company company);

    /**
     * 修改用户信息
     * @param company
     * @return
     */
    ResponseBean updateCompanyInfo(Company company);
}
