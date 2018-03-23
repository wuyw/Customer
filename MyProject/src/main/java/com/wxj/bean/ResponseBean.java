package com.wxj.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求返回实体类
 */
public class ResponseBean {

    /**
     * 请求成功
     */
    public final static Integer CODE_SUCCESS = 200;
    /**
     * 无权限
     */
    public final static Integer CODE_NOAUTH = 201;
    /**
     * 参数错误,为空或不存在
     */
    public final static Integer CODE_NOTVALIDATE = 202;
    /**
     * 查询无结果
     */
    public final static Integer CODE_NO_RESULT = 203;
    /**
     * 参数格式错误
     */
    public final static Integer CODE_FORMAT_ERROR = 204;
    /**
     * 用户名已存在
     */
    public final static Integer CODE_USERNAME_ERROR = 205;
    /**
     * 操作失败
     */
    public final static Integer CODE_FAIL = 207;

    /**
     * 数据库操作失败
     */
    public final static Integer CODE_DB_ERROR = 208;

    /**
     * 区域名重复
     */
    public final static Integer CODE_AREA_NAME_EXIST=209;

    /**
     * 公司名重复
     */
    public final static Integer CODE_COMPANY_NAME_EXIST=210;

    /**
     * 站点名重复
     */
    public final static Integer CODE_SITE_EXIST=211;

    /**
     * 设备id重复
     */
    public final static Integer CODE_DEVICEID_EXIST = 225;

    /**
     * 类型名称重复
     */
    public final static Integer CODE_MODEL_EXIST = 226;

    /**
     * 角色名重复
     */
    public final static Integer CODE_ROLE_EXIST=227;

    /**
     * 密码重复或不对
     */
    public final static Integer CODE_PASSWORD_EXIST=228;

    /**
     * 当前时间戳，秒
     */
    private Long at;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回结果
     */
    private Map<String, Object> result;


    public ResponseBean(Integer code, String err) {
        this.code = code;
        this.at = System.currentTimeMillis() / 1000;
        this.result = new HashMap<>();
        this.result.put("msg", err);
    }

    public ResponseBean() {
        this.at = System.currentTimeMillis() / 1000;
    }

    public Long getAt() {
        return at;
    }

    public void setAt(Long at) {
        this.at = at;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

}
