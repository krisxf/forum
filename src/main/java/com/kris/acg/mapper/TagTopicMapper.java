package com.kris.acg.mapper;

import com.kris.acg.entity.community.TagTopic;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-17 17:25
 **/

@Mapper
public interface TagTopicMapper {
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
