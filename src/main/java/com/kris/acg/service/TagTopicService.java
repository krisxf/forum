package com.kris.acg.service;

import com.kris.acg.entity.community.TagTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-02 15:20
 **/

public interface TagTopicService {
    /**
     * 保存话题对应的标签信息
     * @param topicId 话题id
     * @param tagIdList 标签列表
     */
    void save(long topicId, List<Integer> tagIdList);
    /**
     * 批量查询话题对应的标签
     * @param tagTopicList 话题对应标签列表
     */
    void insertBatch(List<TagTopic> tagTopicList);
    /**
     * 查询话题下对于的标签
     * @param topicId 话题id
     * @return 返回对于的标签id
     */
    List<Integer> selectTagIds(@Param("topicId") long topicId);

    /**
     * 根据标签查询对于的话题
     * @param tagId 标签id
     * @return 返回
     */
    List<Long> selectTopicIds(@Param("tagId") long tagId);

    /**
     * 删除话题数据
     * @param topicId 话题id
     * @return 返回
     */
    int deleteTagTopic(long topicId);
}
