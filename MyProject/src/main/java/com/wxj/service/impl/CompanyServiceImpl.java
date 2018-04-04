package com.wxj.service.impl;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.Company;
import com.wxj.bean.base.User;
import com.wxj.mapper.CompanyMapper;
import com.wxj.mapper.UserMapper;
import com.wxj.service.CompanyService;
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
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    private Logger logger = Logger.getLogger(CompanyService.class);

    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    LogUtil logUtil;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    UserMapper userMapper;


    @Override
    public Company getCompanyByDomain(String domain) {
        return companyMapper.getCompanyByDomain(domain);
    }



    /**
     * 获取企业信息
     * @param companyId
     * @return
     */
    @Override
    public ResponseBean getCompanyInfo(String companyId) {
        ResponseBean responseBean = new ResponseBean();
        Map<String, Object> result = new HashMap<>();
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        Company company = companyMapper.getCompanyInfo(companyId);
        result.put("company",company);
        responseBean.setResult(result);
        return responseBean;
    }

    /**
     * 用户重复性验证
     * @param company
     * @return
     */
    public ResponseBean companyValidate(Company company){
        ResponseBean responseBean = new ResponseBean();
        //验证企业域和账号邮箱是否存在
        List<Company> userList = companyMapper.getCompany(company);
        if (userList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        }
            responseBean.setCode(ResponseBean.CODE_USERNAME_ERROR);
        return responseBean;


    }


    /**
     * 保存用户信息
     * @param company
     * @return
     */
    public ResponseBean saveCompany(Company company){
        ResponseBean responseBean = new ResponseBean();

        //用户填写的信息是否为空
        if (!company.isValid()) {
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }

        //验证企业域和账号邮箱是否存在

        Company companyForDomain = new Company();
        companyForDomain.setCompanyDomain(company.getCompanyDomain());
        List<Company> userList2 = companyMapper.getCompany(companyForDomain);
        if (!userList2.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_domain_EXIST);
            return responseBean;
        }

        Company companyForAccount = new Company();
        companyForAccount.setAccount(company.getAccount());
        List<Company> userList = companyMapper.getCompany(companyForAccount);
        if (!userList.isEmpty()){
            responseBean.setCode(ResponseBean.CODE_account_EXIST);
            return responseBean;
        }

        company.setLinkman("超级管理员");//联系人默认超级管理员
        //插入Company
        int i = companyMapper.saveCompany(company);
        if (i <= 0){
            responseBean.setCode(ResponseBean.CODE_FAIL);
            return responseBean;
        }
        //插入user
        Company company1 = companyMapper.getCompanyByDomain(company.getCompanyDomain());
        User user = new User();
        user.setCompanyId(company1.getId());
        user.setAccount(company1.getAccount());
        user.setName(company1.getLinkman());
        user.setNickname(company1.getLinkman());
        user.setPassword(company1.getPassword());
        user.setMobile(company1.getMobile());
        user.setEmail(company1.getEmail());
        user.setRole(1);//超级管理员
        user.setMaxReception(100);//默认100
        user.setIsDel(0);
        int j = userMapper.insertUser(user);
        if (j <= 0){
            responseBean.setCode(ResponseBean.CODE_FAIL);
            return responseBean;
        }
        responseBean.setCode(ResponseBean.CODE_SUCCESS);
        return responseBean;
    }

    /**
     * 修改企业信息
     * @param company
     * @return
     */
    public ResponseBean updateCompanyInfo(Company company){
        ResponseBean responseBean = new ResponseBean();
        if(!(!ValidateUtil.isEmpty(company.getCompanyName()) && !ValidateUtil.isEmpty(company.getMobile())
                && !ValidateUtil.isEmpty(company.getLinkman()) && !ValidateUtil.isEmpty(company.getEmail()))){
            responseBean.setCode(ResponseBean.CODE_NOTVALIDATE);
            return responseBean;
        }
        int i= companyMapper.updateCompany(company);
        if (i > 0){
            responseBean.setCode(ResponseBean.CODE_SUCCESS);
        } else {
            responseBean.setCode(ResponseBean.CODE_FAIL);
        }
        return responseBean;
    }
}
