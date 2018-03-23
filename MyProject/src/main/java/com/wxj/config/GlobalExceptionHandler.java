package com.wxj.config;

import com.wxj.bean.ResponseBean;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice(annotations=RestController.class)
//@ControllerAdvice(basePackages={"com.xxx","com.ooo"})
@ControllerAdvice(basePackages={"com.sluan.controller","com.sluan.shiro"})
public class GlobalExceptionHandler {
    private final Logger logger = Logger.getLogger(getClass());

    @ExceptionHandler(AuthorizationException.class)
    //    @ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class})
    //    @ExceptionHandler//处理所有异常
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public ResponseBean authExceptionHandler(AuthorizationException e, HttpServletResponse response) {
        logger.error("auth err: ", e);
        ResponseBean resp = new ResponseBean();
        resp.setCode(ResponseBean.CODE_NOAUTH);
        return resp;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public ResponseBean exceptionHandler(Exception e, HttpServletResponse response) {
        logger.error("err: ", e);
        ResponseBean resp = new ResponseBean();
        resp.setCode(ResponseBean.CODE_FAIL);
  //      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return resp;
    }
}