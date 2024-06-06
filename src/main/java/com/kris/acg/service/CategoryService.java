package com.kris.acg.service;

import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.req.CategoryModifyReq;
import com.kris.acg.mapper.CategoryMapper;

import java.util.List;

/**
 * @author Kristin
 */
public interface CategoryService {
    /**
     * 根据id查询分类
     * @param categoryId 分类id
     * @return 返回分类对象
     */
    Category queryCategoryById(int categoryId);

    /**
     * 查询所有分类
     * @return 反馈分类集合
     */
    List<Category> queryAllCategory();

    /**
     * 查询分类的个数
     * @return 返回
     */
    int queryCountCategory();

    /**
     * 插入分类
     * @param label 分类名称
     */
    void addCategory(String  label);

    /**
     * 更新分类内容
     * @param req 分类修改请求对象
     */
    void updateCategory(CategoryModifyReq req);

    /**
     * 删除分类
     * @param categoryId 分类id
     */
    void deleteCategoryById(Integer categoryId);
}
