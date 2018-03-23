package com.wxj.log;

import com.wxj.bean.ResponseBean;
import com.wxj.service.LogInfoService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {
    private Logger logger = Logger.getLogger(LogAspect.class);

    @Autowired
    LogInfoService logInfoService;

    /**
     * <p>Discription:[后置通知，扫描com.sluan包及此包下的所有带有Log注解的方法]</p>
     *
     * @param joinPoint 前置参数
     * @param log       自定义注解
     * @author:[全冉]
     */
    @AfterReturning(pointcut = ("execution(* com.wxj..*.*(..)) && @annotation(log)"), returning = "response")
    public void doAfterAdvice(JoinPoint joinPoint, Log log, ResponseBean response) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if ((!request.getRequestURI().equals("/user/login") && response.getCode().equals(ResponseBean.CODE_SUCCESS))
                || (request.getRequestURI().equals("/user/login") && response.getCode().equals(ResponseBean.CODE_SUCCESS) && response.getResult().get("pass") != null && response.getResult().get("pass").equals("ok"))) {
            getIpAddress(request);
            String value = log.message();
            addLog(request.getRequestURI(), value, joinPoint.getArgs(), log.level(), getIpAddress(request));
        }
    }

    /**
     * 获取真实IP
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * <p>Discription:[保存操作日志]</p>
     */
    public void addLog(String uri, String message, Object[] objects, LogLevel logLevel, String ip) {
        // TODO: 2017/12/7
    }


}