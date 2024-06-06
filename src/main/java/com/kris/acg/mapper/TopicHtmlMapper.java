package com.kris.acg.mapper;

import com.kris.acg.entity.community.TopicHtml;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Kristin
 */
@Mapper
public interface TopicHtmlMapper {
    /**
     * 查询相关话题的html
     * @param topicId 话题id
     * @return 返回
     */
    TopicHtml selectTopicHtmlByTopicId(Long topicId);

    /**
     * 添加
     * @param topicHtml 对象
     * @return 返回
     */
    int insertTopicHtml(TopicHtml topicHtml);

    /**
     * 修改
     * @param topicHtml 对象
     * @return 返回
     */
    int updateTopicHtml(TopicHtml topicHtml);

    /**
     * 删除
     * @param topicId 话题id
     * @return 返回
     */
    int deleteTopicHtml(Long topicId);
}
