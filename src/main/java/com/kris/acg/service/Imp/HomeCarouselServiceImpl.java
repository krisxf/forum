package com.kris.acg.service.Imp;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.kris.acg.common.StorePrefixConstants;
import com.kris.acg.entity.community.HomeCarousel;
import com.kris.acg.entity.req.CarouselAddReq;
import com.kris.acg.entity.req.CarouselModifyReq;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.HomeCarouselMapper;
import com.kris.acg.service.HomeCarouselService;
import com.kris.acg.utils.FileUtil;
import com.kris.acg.utils.MinioUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.kris.acg.utils.FileUtil.*;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-08-19 15:38
 **/

@Service
public class HomeCarouselServiceImpl implements HomeCarouselService {


    @Resource
    MinioUtil minioUtil;

    @Resource
    HomeCarouselMapper homeCarouselMapper;

    @Override
    public void coverHomeCarouselImage(Long id, MultipartFile imageFile) {
        // 获取旧的轮播图对象
        HomeCarousel homeCarousel = homeCarouselMapper.selectById(id);
        if(homeCarousel == null){
            throw new BusinessException("图片不存在");
        }
        //生成新的照片的url
        int size = (int) imageFile.getSize();
        Date now = new Date();
        String oldUrl = homeCarousel.getUrl();
        String suffix = String.format("%s.%s", IdUtil.getSnowflakeNextId(), FileUtil.formatOf(imageFile));
        String newUrl = minioUtil.generateBaseUrl(StorePrefixConstants.HOME_CAROUSEL, now, suffix);
        //更新
        homeCarousel.setUpdateTime(now);
        homeCarousel.setUrl(newUrl);

        homeCarouselMapper.update(homeCarousel);

        //删除原有的图片
        minioUtil.deleteFile(oldUrl);
    }

    @Override
    public List<HomeCarousel> queryCurrent() {
        return homeCarouselMapper.selectCurrent();
    }

    @Override
    public List<HomeCarousel> selectAll() {
        return homeCarouselMapper.selectAll();
    }

    @Override
    public void save(MultipartFile multipartFile, CarouselAddReq carouselAddReq) {
        String description = carouselAddReq.getDescription();
        String link = carouselAddReq.getLink().trim();
        int length = 5;
        String idString = RandomUtil.randomString(length);
        int size = (int) multipartFile.getSize();
        int location = homeCarouselMapper.getMaxLocation() + 1;
        Date now = new Date();
        String fileName = String.format("%s.%s", idString, formatOf(multipartFile));
        String url = minioUtil.generateBaseUrl(StorePrefixConstants.HOME_CAROUSEL, now, fileName);
        String newUrl = minioUtil.upload(multipartFile, url);

        HomeCarousel homeCarousel = new HomeCarousel();
        homeCarousel.setSize(size);
        homeCarousel.setDescription(description);
        homeCarousel.setCreateTime(now);
        homeCarousel.setUpdateTime(now);
        homeCarousel.setUrl(newUrl);
        homeCarousel.setLocation(location);
        homeCarousel.setLink(link);

        homeCarouselMapper.insert(homeCarousel);
    }

    @Override
    public void update(CarouselModifyReq homeCarousel) {
        Long id = homeCarousel.getId();
        HomeCarousel t = homeCarouselMapper.selectById(id);
        t.setDescription(homeCarousel.getDescription());
        t.setLink(homeCarousel.getLink());
        t.setUpdateTime(new Date());
        int update = homeCarouselMapper.update(t);
        if(update == 0){
            throw new BusinessException("轮播图修改错误");
        }
    }

    @Override
    public int delete(Long id) {
        return homeCarouselMapper.delete(id);
    }
}
