package com.wxj.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxj.bean.ResponseBean;
import com.wxj.service.UserService;
import com.wxj.util.RedisUtil;
import com.wxj.util.ValidateUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问控制过滤器
 */
public class StatelessAccessControlFilter extends AccessControlFilter {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * <p>
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * <p>
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，回往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1 ：获取请求参数
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        //对特殊请求直接放行，登录，资源文件
        if (url.equals("/user/login") || url.equals("/") || url.contains("/resources/")|| url.contains("/static/")) {
            return true;
        }else {
            String method=req.getMethod();
            if (method.equalsIgnoreCase("POST")){
                if (url.equals("/user/login/out")){

                } else {
                    return false;
                }
            }
        }
        String jwt = req.getHeader("Authorization");
        if (ValidateUtil.isNullOrEmpty(jwt)) {
            onLoginFail(response);
            return false;
        }
            //2 ：生成无状态Token
        StatelessAuthenticationToken token = new StatelessAuthenticationToken(null,null, jwt.replace("Bearer ",""));
        try {
            //3、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            //4、登录失败
            onLoginFail(response);
            return false;//就直接返回给请求者.
        }
        return true;
    }

    //登录失败时默认返回401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        ResponseBean responseBean = new ResponseBean(ResponseBean.CODE_NOAUTH, "no auth.");
        httpResponse.getWriter().write(mapper.writeValueAsString(responseBean));
    }

}
