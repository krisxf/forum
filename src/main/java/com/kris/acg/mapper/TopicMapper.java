package com.kris.acg.mapper;

import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.req.TopicSearchReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface TopicMapper {
    /**
     * 叠加一个话题的浏览量
     * @param topicId 话题
     * @param increment 增量
     * @return 返回
     */
    int updateTopicPageView(Long topicId,int increment);

    /**
     * 更新话题的活跃时间
     * @param topicId 话题id
     * @param activityTime 活跃时间
     * @return 返回
     */
    int updateActivityTimeInt(Long topicId, Date activityTime);

    /**
     * 给话题的评论叠加
     * @param increment 叠加量
     * @param topicId 话题id
     * @return 返回
     */
    int updateComment(Long topicId, int increment);

    /**
     * 给话题的点赞叠加
     * @param increment 叠加量
     * @param topicId 话题id
     * @return 返回
     */
    int updateStar(Long topicId,int increment);

    /**
     * 根据查询的参数来查询 话题
     * @param topicSearchReq 查询的参数
     * @return 返回
     */
    List<Topic> selectTopicByReq(@Param("topicSearchReq") TopicSearchReq topicSearchReq);

    /**
     * 根据给的话题id来查询话题
     * @param ids id集合
     * @return 返回
     */
    List<Topic> selectTopicsByTagIds(@Param("ids") List<Long> ids);

    /**
     * 根据某种排序方式查询话题
     * @param column 排序方式
     * @return 返回话题的集合
     */
    List<Topic> selectTopicBySortBy(String column);

    /**
     * 查询所有的文章
     * @return 返回
     */
    List<Topic> selectAll();

    /**
     * 查询某一分类下的话题
     * @param categoryId 分类id
     * @return 返回
     */
    List<Topic> selectTopicByCategoryId(int categoryId);

    /**
     * 查询用户发布的所有话题
     * @param userId 用户id
     * @return 返回
     */
    List<Topic> selectUserTopic(Long userId);

    /**
     * 根据id查询话题
     * @param topicId 话题id
     * @return 返回
     */
    Topic selectTopicById(Long topicId);

    /**
     * 查询用户发布话题的数量
     * @param userId 用户id
     * @return 返回
     */
    int selectCountUserTopic(Long userId);

    /**
     * 查询话题
     * @param topic 话题对象
     * @return 返回
     */
    int insertTopic(Topic topic);

    /**
     * 修改话题
     * @param topic 话题对象
     * @return 返回
     */
    int updateTopic(Topic topic);

    /**
     * 删除话题
     * @param topicId 话题id
     * @return 返回
     */
    int deleteTopicById(Long topicId);
}
