package com.wxj.service;

import com.wxj.bean.base.LogInfo;

public interface LogInfoService {
    /**
     * 添加日志
     *
     * @param logInfo
     */
    void save(LogInfo logInfo);
}
