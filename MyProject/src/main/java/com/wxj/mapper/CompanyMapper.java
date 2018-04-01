package com.wxj.mapper;

import com.wxj.bean.base.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CompanyMapper {

    /**
     * 根据企业域获取公司信息
     *
     * @param domain
     * @return
     */
    @Select("SELECT id,company_name AS companyName,account,`password`,company_domain AS companyDomain,mobile,email,create_time AS createTime,linkman FROM company_info  WHERE company_domain = #{domain}")
    Company getCompanyByDomain(@Param("domain")String domain);

    /**
     * 获取用户信息
     *
     * @param companyId
     * @return
     */
    @Select("SELECT id,company_name AS companyName,account,`password`,company_domain AS companyDomain,mobile,email,create_time AS createTime,linkman FROM company_info WHERE id = #{companyId}")
    Company getCompanyInfo(@Param("companyId")String companyId);

    /**
     * 用户重复性验证
     * @param company
     * @return
     */
    @Select("SELECT * FROM company_info WHERE account = #{account} OR company_domain = #{companyDomain}")
    List<Company> getCompany(Company company);

    /**
     * 保存用户信息
     * @param company
     * @return
     */
    @Insert("INSERT INTO company_info ( company_name, account, `password`, linkman, company_domain,mobile, create_time) VALUES (#{companyName},#{account},#{password},#{linkman},#{companyDomain},#{mobile},Now())")
    int saveCompany(Company company);

    /**
     * 更新用户信息
     * @param company
     * @return
     */
    @Update("UPDATE company_info SET company_name = #{companyName},linkman=#{linkman},mobile=#{mobile},email=#{email} WHERE id =#{id}")
    int updateCompany(Company company);

}
