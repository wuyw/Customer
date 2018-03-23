package com.wxj.mapper;

import com.wxj.bean.base.LogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogInfoMapper {

    @Insert("INSERT INTO loginfo(userid,username,ipaddress,before,operation,after,createtime) VALUE( #{logInfo.userId},#{logInfo.username},#{logInfo.ipAddress},#{logInfo.before},#{logInfo.operation},#{logInfo.after},NOW())")
    void save(@Param("logInfo") LogInfo logInfo);

}
