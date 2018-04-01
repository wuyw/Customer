package com.wxj.bean.dto;

import com.wxj.bean.base.User;

public class UserDto extends User {
    private int page;//当前页
    private int perPage;//每页的条数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
