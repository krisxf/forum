package com.kris.acg.service;

import com.kris.acg.entity.community.TopicHtml;

/**
 * @author Kristin
 */
public interface TopicHtmlService {
    /**
     * 查询相关话题的html
     * @param topicId 话题id
     * @return 返回
     */
    TopicHtml queryTopicHtmlByTopicId(Long topicId);

    /**
     * 添加
     * @param topicHtml 对象
     * @return 返回
     */
    int addTopicHtml(TopicHtml topicHtml);

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
