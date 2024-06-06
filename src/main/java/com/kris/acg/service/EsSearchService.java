package com.kris.acg.service;

import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.TopicQueryByCategoryIdReq;
import com.kris.acg.entity.req.TopicQueryBySortReq;
import com.kris.acg.entity.req.TopicQueryByTagIdReq;
import com.kris.acg.entity.req.TopicSearchReq;

import java.util.List;

/**
 * @author Kristin
 */
public interface EsSearchService {
    /**
     * 查询相似的话题
     * @param topicId 话题id
     * @return 返回
     */
    List<Topic> searchSimilar(long topicId);

    /**
     * 根据标题查询相似的话题
     * @param topicId 话题id
     * @param title 标题
     * @return 返回
     */
    List<Topic> searchSimilarByTitle(long topicId,String title);

    /**
     * 根据标签id查询话题
     * @param queryReq 请求对象
     * @return 封装
     */
    List<Topic> searchByTagId(TopicQueryByTagIdReq queryReq);

    /**
     * 根据分类的id查询话题
     * @param queryReq 请求对象
     * @return 返回
     */
    List<Topic> searchBySort(TopicQueryByCategoryIdReq queryReq);

    /**
     * 根据排序的方式不同查询
     * @param queryReq 请求对象
     * @return 返回
     */
    List<Topic> searchBySort(TopicQueryBySortReq queryReq);

    /**
     * 查询所有话题
     * @param topicSearchReq 请求对象
     * @return 返回
     */
    List<Topic> search(TopicSearchReq topicSearchReq);
}
