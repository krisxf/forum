package com.kris.acg.mapper;

import com.kris.acg.entity.community.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface TagMapper {

    /**
     * 根据id查询标签对象
     * @param id id
     * @return 返回对象
     */
    Tag selectTagById(Integer id);

    /**
     * 查询所有标签
     * @return 反馈标签集合
     */
    List<Tag> selectAllTag();


    /**
     * 查询标签的个数
     * @return 返回
     */
    int selectCountTag();

    /**
     * 插入标签
     * @param tag 标签对象
     * @return 返回
     */
    int insertTag(Tag tag);

    /**
     * 更新标签内容
     * @param tag 标签对象
     * @return 返回
     */
    int updateTag(Tag tag);

    /**
     * 删除标签
     * @param tagId 标签id
     * @return 返回
     */
    int deleteTagById(Integer tagId);
}
