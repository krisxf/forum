package com.kris.acg.mapper;

import com.kris.acg.entity.community.HomeCarousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface HomeCarouselMapper {
    /**
     * 根据id查询轮播图
     * @param id id
     * @return 返回轮播图对象
     */
    HomeCarousel selectById(Long id);

    /**
     * 获取最大的位置
     * @return 返回
     */
    Integer getMaxLocation();
    /**
     * 查询正在轮播的图片
     * @return 返回
     */
    List<HomeCarousel> selectCurrent();

    /**
     * 查询所有
     * @return 返回
     */
    List<HomeCarousel> selectAll();

    /**
     * 插入数据
     * @param homeCarousel 插入
     * @return 返回
     */
    int insert(HomeCarousel homeCarousel);

    /**
     * 修改
     * @param homeCarousel 对象
     * @return 返回
     */
    int update(HomeCarousel homeCarousel);

    /**
     * 删除
     * @param id id
     * @return 返回
     */
    int delete(Long id);
}
