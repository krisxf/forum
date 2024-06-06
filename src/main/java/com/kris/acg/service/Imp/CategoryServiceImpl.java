package com.kris.acg.service.Imp;

import com.kris.acg.entity.community.Category;
import com.kris.acg.entity.req.CategoryModifyReq;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.CategoryMapper;
import com.kris.acg.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-17 18:15
 **/

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public Category queryCategoryById(int categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }

    @Override
    public List<Category> queryAllCategory() {
        return categoryMapper.selectAllCategory();
    }

    @Override
    public int queryCountCategory() {
        return categoryMapper.selectCountCategory();
    }

    @Override
    public void addCategory(String label ) {
        Category category = new Category();
        category.setLabel(label);
        Date date = new Date();
        category.setUpdateTime(date);
        category.setCreateTime(date);
        category.setDeleted(false);

        int i = categoryMapper.insertCategory(category);
        if(i < 0){
            throw new BusinessException("插入失败");
        }
    }

    @Override
    public void updateCategory(CategoryModifyReq req) {
        Category category = categoryMapper.selectCategoryById(req.getId());
        category.setUpdateTime(new Date());
        category.setLabel(req.getLabel());
        int i = categoryMapper.updateCategory(category);
        if(i < 0){
            throw new BusinessException("修改分类错误");
        }
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        int i = categoryMapper.deleteCategoryById(categoryId);
        if(i < 0){
            throw new BusinessException("删除分类错误");
        }
    }
}
