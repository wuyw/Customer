package com.wxj.util;

/**
 * Created by betty77 on 2017/6/27.
 */
public class PageUtil {
    public static final String PER_PAGE = "per_page"; //每页记录数
    public static final String PAGE = "page"; //页数
    public static final String TOTAL_PAGE = "total_page"; //每页记录数
    public static final String TOTAL_COUNT = "total_count"; //总记录数

    /**
     * 获取起始条数
     *
     * @param page
     * @param per_page
     * @return
     */
    public static Integer getStart(Integer page, Integer per_page) {
        return ((page < 1 ? 1 : page) - 1) * per_page;
    }

    /**
     * 计算页数
     *
     * @param total
     * @param per_page
     * @return
     */
    public static Integer getPageSize(Integer total, Integer per_page) {
        if (total == 0) {
            return 1;
        }
        if (total % per_page > 0) {
            return total / per_page + 1;
        } else {
            return total / per_page;
        }
    }
}
