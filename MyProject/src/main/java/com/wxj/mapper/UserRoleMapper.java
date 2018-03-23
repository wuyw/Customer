package com.wxj.mapper;

import com.wxj.bean.RoleBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserRoleMapper {

    List<RoleBean> getRoleByUserId(@Param("id") String id);
}
