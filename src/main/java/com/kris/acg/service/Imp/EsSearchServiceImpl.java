package com.kris.acg.service.Imp;

import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.TopicQueryByCategoryIdReq;
import com.kris.acg.entity.req.TopicQueryBySortReq;
import com.kris.acg.entity.req.TopicQueryByTagIdReq;
import com.kris.acg.entity.req.TopicSearchReq;

import com.kris.acg.service.EsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-10-03 20:07
 **/

@Service
@Slf4j
public class EsSearchServiceImpl implements EsSearchService {

//    @Resource
//    TopicRepository topicRepository;

    @Override
    public List<Topic> searchSimilar(long topicId) {
        return null;
    }

    @Override
    public List<Topic> searchSimilarByTitle(long topicId, String title) {
        return null;
    }

    @Override
    public List<Topic> searchByTagId(TopicQueryByTagIdReq queryReq) {
        return null;
    }

    @Override
    public List<Topic> searchBySort(TopicQueryByCategoryIdReq queryReq) {
        return null;
    }

    @Override
    public List<Topic> searchBySort(TopicQueryBySortReq queryReq) {
        return null;
    }

    @Override
    public List<Topic> search(TopicSearchReq topicSearchReq) {
        return null;
    }
}
