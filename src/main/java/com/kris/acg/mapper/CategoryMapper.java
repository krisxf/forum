package com.kris.acg.mapper;

import com.kris.acg.entity.community.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface CategoryMapper {
    /**
     * 根据id查询分类
     * @param categoryId 分类id
     * @return 返回分类对象
     */
    Category selectCategoryById(int categoryId);

    /**
     * 查询所有分类
     * @return 反馈分类集合
     */
    List<Category> selectAllCategory();

    /**
     * 查询分类的个数
     * @return 返回
     */
    int selectCountCategory();

    /**
     * 插入分类
     * @param category 分类对象
     * @return 返回
     */
    int insertCategory(Category category);

    /**
     * 更新分类内容
     * @param category 分类对象
     * @return 返回
     */
    int updateCategory(Category category);

    /**
     * 删除分类
     * @param categoryId 分类id
     * @return 返回
     */
    int deleteCategoryById(Integer categoryId);
}
