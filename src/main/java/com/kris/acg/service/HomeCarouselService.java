package com.kris.acg.service;

import com.kris.acg.entity.community.HomeCarousel;
import com.kris.acg.entity.req.CarouselAddReq;
import com.kris.acg.entity.req.CarouselModifyReq;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Kristin
 */
public interface HomeCarouselService {
    /**
     * 覆盖原本轮播图的照片
     * @param id id
     * @param imageFile 照片文件
     */
    void coverHomeCarouselImage(Long id,MultipartFile imageFile);

    /**
     * 查询正在轮播的图片
     * @return 返回
     */
    List<HomeCarousel> queryCurrent();

    /**
     * 查询所有
     * @return 返回
     */
    List<HomeCarousel> selectAll();

    /**
     * 插入数据
     * @param multipartFile 插入
     * @param carouselAddReq 封装的请求参数
     */
    void save(MultipartFile multipartFile, CarouselAddReq carouselAddReq);

    /**
     * 修改
     * @param homeCarousel 对象
     */
    void update(CarouselModifyReq homeCarousel);

    /**
     * 删除
     * @param id id
     * @return 返回
     */
    int delete(Long id);
}
