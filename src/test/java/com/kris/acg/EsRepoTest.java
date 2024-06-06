//package com.kris.acg;
//
//import com.alibaba.fastjson.JSONObject;
//import com.kris.acg.entity.community.Topic;
//import com.kris.acg.entity.index.TopicIndex;
//import com.kris.acg.mapper.TopicMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @Program: acg
// * @Description:
// * @Author: kris
// * @Create: 2023-10-02 16:22
// **/
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class EsRepoTest {
//    @Autowired
//    private TopicRepository topicRepository;
//    @Autowired
//    TopicMapper topicMapper;
//    @Autowired
//    RestHighLevelClient restHighLevelClient;
//
//    @Test
//    public void testInsert(){
//        Topic topic = topicMapper.selectTopicById(1700460012117204992L);
//        System.out.println(topic.toString());
//        topicRepository.save(TopicIndex.from(topic));
//    }
//
//    @Test
//    public void testInsertAll(){
//        List<TopicIndex> topics = TopicIndex.toTopicIndexList(topicMapper.selectTopicByCategoryId(1));
//        System.out.println(topics);
//        topicRepository.saveAll(topics);
//    }
//
//
//    @Test
//    public void testDeleteAll(){
//        topicRepository.deleteAll();
//    }
//
//    @Test
//    public void testSearchByRepository(){
//        Query query = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.multiMatchQuery("动漫寒冬","title","content"))
//                .withSort(SortBuilders.fieldSort("starCount").order(SortOrder.DESC))
//                .withPageable(PageRequest.of(0,10))
//                .withHighlightFields(
//                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
//                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
//                ).build();
//        Page<TopicIndex> search = topicRepository.search(query);
//        System.out.println(search.getTotalElements());
//        System.out.println(search.getTotalPages());
//        System.out.println(search.getNumber());
//        System.out.println(search.getSize());
//        for (TopicIndex topic: search
//             ) {
//            System.out.println(topic);
//        }
//    }
//
//
//    @Test public void highlightQuery() throws Exception{
//        //1.创建搜索请求 searchRequest  topic是索引名，就是表名
//        SearchRequest searchRequest = new SearchRequest("topic");
//        // 2.配置高亮 HighlightBuilder
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("title");
//        // 为哪些字段匹配到的内容设置高亮
//        highlightBuilder.field("content");
//        highlightBuilder.requireFieldMatch(false);
//        highlightBuilder.preTags("<span style='color:red'>");
//        // 相当于把结果套了一点html标签  然后前端获取到数据就直接用
//         highlightBuilder.postTags("</span>");
//        // 3.构建搜索条件 searchSourceBuilder
//         SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
//                .query(QueryBuilders.multiMatchQuery("动漫寒冬", "title", "content"))
//                .sort(SortBuilders.fieldSort("starCount").order(SortOrder.DESC))
//                 // 指定从哪条开始查询
//                .from(0)
//                 // 需要查出的总记录条数
//                .size(10)
//                 //配置高亮
//                .highlighter(highlightBuilder);
//                //4.将搜索条件参数传入搜索请求
//        searchRequest.source(searchSourceBuilder);
//        //5.使用客户端发送请求
//        SearchResponse searchResponse = null;
//        try {
//            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        }catch (Throwable e){
//            throw new RuntimeException(e);
//        }
//        List<TopicIndex> list = new LinkedList<>();
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            TopicIndex discussPost = JSONObject.parseObject(hit.getSourceAsString(), TopicIndex.class);
//            // 处理高亮显示的结果
//            HighlightField titleField = hit.getHighlightFields().get("title");
//            if (titleField != null) {
//                //title=<span style='color:red'>互联网</span>求职暖春计划...
//                discussPost.setTitle(titleField.getFragments()[0].toString());
//            }
//            HighlightField contentField = hit.getHighlightFields().get("content");
//            if (contentField != null) {
//                //content=它是最时尚的<span style='color:red'>互联网</span>公司之一...
//                discussPost.setContent(contentField.getFragments()[0].toString());
//            }
//            System.out.println(discussPost);
//            list.add(discussPost);
//        }
//
//
//        for (TopicIndex topicIndex: list
//             ) {
//            System.out.println(topicIndex);
//        }
//    }
//
//}
