package com.kris.acg.task;

import com.kris.acg.common.KeyNames;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.service.TopicService;
import com.kris.acg.utils.RedisUtil;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-09-20 17:08
 **/

@Component
public class PageViewTask {

    @Resource
    RedisUtil redisUtil;
    @Resource
    TopicService topicService;

    @Scheduled(fixedRate = 5000)
    public void addPageView(){
        //从缓存中获取所有关于pageView的键
        List<String> keysWithPrefix = redisUtil.getKeysWithPrefix(KeyNames.TOPIC_PAGE_VIEW);
        int length = KeyNames.TOPIC_PAGE_VIEW.length();
        //遍历键值，从中获取topicId，并将增量持久化到数据库中
        for (String str: keysWithPrefix
             ) {
            String topicIdStr = str.substring(length);
            Long topicId = Long.parseLong(topicIdStr);
            int pageView = (int) redisUtil.get(str);
            Topic topic = topicService.queryTopicById(topicId);
            boolean equals = topic.getPageView().equals(pageView);
            //判断浏览量有无增长，有增长就将增量同步
            if(!equals){
                int increment = pageView - topic.getPageView();
                topicService.updateTopicPageView(topicId,increment);
            }
        }
    }
}
