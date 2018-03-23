package com.wxj.service.impl;

import com.wxj.bean.RoleBean;
import com.wxj.mapper.UserRoleMapper;
import com.wxj.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<RoleBean> getRolesByUserId(String id) {
        return userRoleMapper.getRoleByUserId(id);
    }
}
