package com.wxj.service.impl;

import com.wxj.bean.base.LogInfo;
import com.wxj.mapper.LogInfoMapper;
import com.wxj.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    LogInfoMapper logInfoMapper;

    @Override
    public void save(LogInfo logInfo) {
        logInfoMapper.save(logInfo);
    }
}
