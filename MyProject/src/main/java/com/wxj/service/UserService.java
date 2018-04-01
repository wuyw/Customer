package com.wxj.service;

import com.wxj.bean.LoginUser;
import com.wxj.bean.ResponseBean;
import com.wxj.bean.base.User;
import com.wxj.bean.dto.UserDto;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

public interface UserService {
    /**
     * 新增客服人员
     * @param user
     * @return
     */
    ResponseBean insertUser(User user);

    /**
     * 登录
     *
     * @param loginUser
     * @return
     */
    ResponseBean toLogin(LoginUser loginUser);

    /**
     * 根据公司ID和账号查询用户
     * @param companyId,account
     * @return
     */
    User getUserByCompanyIdAndAccount(Integer companyId,String account);


    /**
     * 查询客服人员分页
     * @param page
     * @param page
     * @param companyId
     * @return
     */
    ResponseBean getUserList(Integer page,Integer perPage,Integer companyId);

    /**
     * 获取公司对应客服人员的数量
     * @param companyId
     * @return
     */
    int getUserCount(Integer companyId);

    /**
     * 根据用户ID查询客服信息
     * @param id
     * @return
     */
    ResponseBean getUserById(Integer id);

    /**
     * 模糊查询
     * @param user
     * @return
     */
    ResponseBean getUserByParams(User user,Integer page,Integer perPage);

    /**
     * 删除客服人员
     * @param id
     * @return
     */
    ResponseBean delUser(Integer id);
}
