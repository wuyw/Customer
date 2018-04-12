package com.wxj.bean.Request;

/**
 * Created by WangXiaojian on 2018/4/4 0004.
 */
public class RequestObject {
    private Integer page;
    private Integer perPage;
    private String keywords;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
