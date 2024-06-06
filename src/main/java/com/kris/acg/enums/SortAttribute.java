package com.kris.acg.enums;

/**
 * @Program: acg
 * @Description: 排序方式的枚举
 * @Author: kris
 * @Create: 2023-08-19 18:50
 **/

public enum SortAttribute {
    /**
     * 星数
     */
    STAR_COUNT("star_count"),
    /**
     * 浏览量
     */
    PAGE_VIEW("page_view"),
    /**
     * 评论量
     */
    COMMENT_COUNT("comment_count"),
    /**
     * 创建时间
     */
    CREATE_TIME("create_time"),
    /**
     * 活跃时间
     */
    ACTIVITY_TIME("activity_time");

    private final String value;

    SortAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

