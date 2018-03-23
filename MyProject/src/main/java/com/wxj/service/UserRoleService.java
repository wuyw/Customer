package com.wxj.service;


import com.wxj.bean.RoleBean;

import java.util.List;

public interface UserRoleService {
    /**
     * 获取用户角色
     *
     * @param id
     * @return
     */
    List<RoleBean> getRolesByUserId(String id);
}
