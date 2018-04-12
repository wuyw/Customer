package com.wxj.mapper;


import com.wxj.bean.base.User;
import com.wxj.bean.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 新增客服人员
     * @param user
     * @return
     */
    @Insert("INSERT INTO user_info(company_id,account,`name`,nickname,mobile,email,`password`,role,max_reception ,is_del ) VALUES " +
            "(#{companyId},#{account},#{name},#{nickname},#{mobile},#{email},#{password},#{role},#{maxReception},#{isDel})")
    int insertUser(User user);

    /**
     * 根据公司ID和账号查询用户
     * @param companyId
     * @param account
     * @return
     */
    @Select("SELECT id,company_id AS companyId,account,`name`,nickname,mobile,email,`password`,role,max_reception AS maxReception,is_del AS isDel FROM user_info " +
            "WHERE company_id = #{companyId} AND account = #{account} AND is_del = 0")
    User getUserByCompanyIdAndAccount(@Param("companyId")Integer companyId,@Param("account")String account);

    /**
     * 客服人员列表分页
     * @param companyId
     * @param start
     * @param end
     * @return
     */
    @Select("SELECT id,company_id AS companyId,account,`name`,nickname,mobile,email,`password`,role,max_reception AS maxReception,is_del AS isDel FROM user_info " +
            "WHERE company_id = #{companyId} AND is_del = 0 LIMIT #{start},#{end}")
    List<User> getUserList(@Param("companyId")Integer companyId,@Param("start")int start,@Param("end")int end);

    /**
     * 获取公司对应客服人员的数量
     * @param companyId
     * @return
     */
    @Select("SELECT COUNT(*) FROM user_info WHERE company_id = #{companyId} AND is_del = 0 ")
    int getUserCount(@Param("companyId")Integer companyId);

    /**
     * 根据ID查询客服信息
     * @param id
     * @return
     */
    @Select("SELECT id,company_id AS companyId,account,`name`,nickname,mobile,email,`password`,role,max_reception AS maxReception,is_del AS isDel FROM user_info " +
            "WHERE id = #{id} AND is_del = 0")
    User getUserById(@Param("id")Integer id);

    /**
     * 模糊查询
     * @param
     * @return
     */
    @Select("SELECT id,company_id AS companyId,account,`name`,nickname,mobile,email,role,max_reception AS maxReception,is_del AS isDel " +
            "FROM user_info " +
            "WHERE company_id=#{companyId} AND is_del = 0 " +
            "AND (name LIKE CONCAT('%','${keywords}','%' ) OR nickname LIKE CONCAT('%','${keywords}','%' ) OR account LIKE CONCAT('%','${keywords}','%' ) " +
            "OR mobile LIKE CONCAT('%','${keywords}','%')) "+
            "LIMIT #{start},#{end} ")
    List<User> getUserListByParams(@Param("keywords") String keywords,@Param("companyId")Integer companyId,@Param("start")Integer start,@Param("end")Integer end);

    /**
     * 获取模糊查询的数量
     * @param keywords
     * @param companyId
     * @return
     */
    @Select("SELECT COUNT(*)" +
            "FROM user_info " +
            "WHERE company_id=#{companyId} AND is_del = 0 " +
            "AND (name LIKE CONCAT('%','${keywords}','%' ) OR nickname LIKE CONCAT('%','${keywords}','%' ) OR account LIKE CONCAT('%','${keywords}','%' ) " +
            "OR mobile LIKE CONCAT('%','${keywords}','%'))")
    int getUserCountByParams(@Param("keywords") String keywords,@Param("companyId")Integer companyId);

    /**
     * 删除客服人员
     * @param id
     * @return
     */
    @Update("UPDATE user_info SET is_del = 1 WHERE id = #{id}")
    int delUser(Integer id);

    /**
     * 修改客服人员信息
     * @param user
     * @return
     */
    @Update("UPDATE user_info " +
            "SET name = #{name},nickname = #{nickname},mobile = #{mobile},email = #{email},role = #{role},max_reception = #{maxReception} " +
            "WHERE id = #{id}")
    int updateUser(User user);
}
