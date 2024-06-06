package com.kris.acg.service.Imp;

import com.kris.acg.entity.community.TopicHtml;
import com.kris.acg.mapper.TopicHtmlMapper;
import com.kris.acg.service.TopicHtmlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-19 20:44
 **/

@Service
public class TopicHtmlServiceImpl implements TopicHtmlService {

    @Resource
    TopicHtmlMapper topicHtmlMapper;

    @Override
    public TopicHtml queryTopicHtmlByTopicId(Long topicId) {
        return topicHtmlMapper.selectTopicHtmlByTopicId(topicId);
    }

    @Override
    public int addTopicHtml(TopicHtml topicHtml) {
        return topicHtmlMapper.insertTopicHtml(topicHtml);
    }

    @Override
    public int updateTopicHtml(TopicHtml topicHtml) {
        return topicHtmlMapper.updateTopicHtml(topicHtml);
    }

    @Override
    public int deleteTopicHtml(Long topicId) {
        return topicHtmlMapper.deleteTopicHtml(topicId);
    }
}
