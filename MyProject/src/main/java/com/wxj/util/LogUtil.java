package com.wxj.util;

import com.wxj.bean.base.LogInfo;
import com.wxj.mapper.LogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LogUtil {

    @Autowired
    LogInfoMapper logInfoMapper;

    public String addLog(String ip,String message,String username) {
        if (!ValidateUtil.isNullOrEmpty(message) && !ValidateUtil.isNullOrEmpty(username)){
            LogInfo logInfo = new LogInfo();
            logInfo.setCreateTime(new Date());
            logInfo.setUsername(username);
            logInfo.setOperation(message);
            logInfo.setIpAddress(ip);
            logInfoMapper.save(logInfo);
        } else {
            return null;
        }
        return "add log success";
    }


}
