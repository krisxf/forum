package com.kris.acg.service.Imp;

import cn.hutool.core.util.IdUtil;
import com.kris.acg.entity.community.TagTopic;
import com.kris.acg.mapper.TagTopicMapper;
import com.kris.acg.service.TagTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-02 15:21
 **/

@Slf4j
@Service
public class TagTopicServiceImpl implements TagTopicService {

    @Resource
    TagTopicMapper tagTopicMapper;

    @Override
    public void save(long topicId, List<Integer> tagIdList) {

        if(tagIdList.isEmpty()){
            return ;
        }
        Date date = new Date();
        List<TagTopic> tagTopics = new ArrayList<>();
        for (Integer tagId : tagIdList
                ) {
            TagTopic tagTopic = new TagTopic();
            tagTopic.setTopicId(topicId);
            tagTopic.setTagId(tagId);
            tagTopic.setCreateTime(date);
            tagTopic.setId(IdUtil.getSnowflakeNextId());
            tagTopics.add(tagTopic);
        }

        tagTopicMapper.insertBatch(tagTopics);
    }

    @Override
    public void insertBatch(List<TagTopic> tagTopicList) {
        tagTopicMapper.insertBatch(tagTopicList);
    }

    @Override
    public List<Integer> selectTagIds(long topicId) {
        return tagTopicMapper.selectTagIds(topicId);
    }

    @Override
    public List<Long> selectTopicIds(long tagId) {
        return tagTopicMapper.selectTopicIds(tagId);
    }

    @Override
    public int deleteTagTopic(long topicId) {
        return 0;
    }
}
