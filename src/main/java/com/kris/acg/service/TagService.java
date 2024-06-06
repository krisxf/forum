package com.kris.acg.service;

import com.kris.acg.entity.community.Tag;
import com.kris.acg.entity.req.CategoryModifyReq;
import com.kris.acg.entity.req.TagModifyReq;

import java.util.List;

/**
 * @author Kristin
 */
public interface TagService {
    /**
     * 查询所有标签
     * @return 反馈标签集合
     */
    List<Tag> searchAllTag();

    /**
     * 查询标签的个数
     * @return 返回
     */
    int searchCountTag();

    /**
     * 插入标签
     * @param tagName 新增标签名
     */
    void addTag(String tagName);

    /**
     * 更新标签内容
     * @param tagModifyReq 标签修改请求对象
     */
    void updateTag(TagModifyReq tagModifyReq);

    /**
     * 删除标签
     * @param tagId 标签id
     */
    void deleteTagById(Integer tagId);
}
