package com.kris.acg.service.Imp;

import com.kris.acg.entity.community.Tag;
import com.kris.acg.entity.req.TagModifyReq;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.TagMapper;
import com.kris.acg.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-17 15:33
 **/

@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;

    @Override
    public List<Tag> searchAllTag() {
        return tagMapper.selectAllTag();
    }

    @Override
    public int searchCountTag() {
        return tagMapper.selectCountTag();
    }

    @Override
    public void addTag(String tagName) {
        //构建Tag对象
        Tag tag = new Tag();
        Date date = new Date();

        tag.setCreateTime(date);
        tag.setUpdateTime(date);
        tag.setLabel(tagName);
        tag.setDeleted(false);

        int i = tagMapper.insertTag(tag);
        if(i < 0){
            throw new BusinessException("插入标签错误");
        }
    }

    @Override
    public void updateTag(TagModifyReq tagModifyReq) {
        Integer id = tagModifyReq.getId();
        String label = tagModifyReq.getLabel();
        //根据id查询原本的tag信息
        Tag tag = tagMapper.selectTagById(id);
        tag.setLabel(label);
        tag.setUpdateTime(new Date());

        int i = tagMapper.updateTag(tag);
        if(i < 0){
            throw new BusinessException("修改标签错误");
        }
    }

    @Override
    public void deleteTagById(Integer tagId) {
        int i = tagMapper.deleteTagById(tagId);
        if(i < 0){
            throw new BusinessException("删除分类错误");
        }
    }


}
