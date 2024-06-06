package com.kris.acg.entity.index;
import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kris.acg.common.EsConstants;
import com.kris.acg.entity.community.Topic;
import com.kris.acg.entity.user.UserBasic;
import com.kris.acg.utils.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-10-02 20:32
 **/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
//@Document(indexName = "topic")
public class TopicIndex {

    @Id
    private Long id;

//    @Field(type = FieldType.Text)
    private String all;

//    @Field(type = FieldType.Keyword, store = true)
    private Long userId;
//    @Field(type = FieldType.Text, analyzer = EsConstants.IK_MAX_WORD, store = true)
    private String username;
//    @Field(type = FieldType.Keyword, index = false, store = true)
    private String photoUrl;

//    @Field(type = FieldType.Keyword, analyzer = EsConstants.IK_MAX_WORD, store = true, copyTo = "all")
    private String content;

//    @Field(type = FieldType.Keyword, analyzer = EsConstants.IK_MAX_WORD, store = true, copyTo = "all")
    private String title;

//    @Field(type = FieldType.Integer, store = true)
    private Integer starCount;
//    @Field(type = FieldType.Integer, store = true)
    private Integer pageView;
//    @Field(type = FieldType.Integer, store = true)
    private Integer commentCount;

//    @Field(store = true, index = false, type = FieldType.Keyword)
    private String coverImageUrl;

//    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern =  "yyyy-MM-dd HH:mm:ss", store = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
//    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern =  "yyyy-MM-dd HH:mm:ss", store = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
//    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern =  "yyyy-MM-dd HH:mm:ss", store = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date activityTime;

//    @Field(type = FieldType.Keyword, store = true)
    List<Integer> tagIds;
//    @Field(type = FieldType.Keyword, store = true)
    Integer categoryId;

    public static TopicIndex from(Topic topic) {
        TopicIndex topicIndex = BeanUtils.copyPropertiesTo(topic, TopicIndex.class);
        UserBasic user = topic.getUser();
        if (user != null) {
            topicIndex.setUsername(user.getUsername());
            topicIndex.setPhotoUrl(user.getPhotoUrl());
        }

        return topicIndex;
    }

    public static Topic toTopic(TopicIndex topicIndex) {
        Topic topic = BeanUtils.copyPropertiesTo(topicIndex, Topic.class);
        UserBasic user = new UserBasic();
        user.setUsername(topicIndex.getUsername());
        user.setPhotoUrl(topicIndex.getPhotoUrl());
        topic.setUser(user);
        return topic;
    }

    public static List<Topic> toTopicList(List<TopicIndex> topicIndexList) {
        return topicIndexList.stream()
                .map(TopicIndex::toTopic)
                .collect(Collectors.toList());
    }
    public static List<TopicIndex> toTopicIndexList(List<Topic> topicList) {
        return topicList.stream()
                .map(TopicIndex::from)
                .collect(Collectors.toList());
    }
}
