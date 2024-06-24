package com.kris.acg.mapper;

import com.kris.acg.entity.rbac.Api;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface ApiMapper {
    /**
     * 根据关键字查询所有api
     * @param keyWord 关键字
     * @return 返回api集合
     */
    List<Api> selectApiByKeyWord(String keyWord);

    /**
     * 根据id 查询api
     * @param id api 的 id
     * @return 返回
     */
    Api selectApiById(Integer id);

    /**
     * 增加一个新的api
     * @param api api对象
     * @return return
     */
    int addApi(Api api);

    /**
     * 删除api
     * @param id 需要删除的api的id
     * @return return
     */
    int deleteApi(Integer id);

    /**
     * 更新api信息
     * @param api api对象
     * @return return
     */
    int updateApi(Api api);
}
