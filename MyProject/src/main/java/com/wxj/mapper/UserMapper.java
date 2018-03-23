package com.wxj.mapper;

import com.wxj.bean.base.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @Select("SELECT * FROM user_info WHERE account = #{username} AND company_domain = #{domain}")
    User getUserByUsernameAndDomain(@Param("username")String username,@Param("domain")String domain);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM tb_base_user WHERE id = #{userId}")
    User getUserInfo(@Param("userId")String userId);
}
