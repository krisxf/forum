package com.kris.acg.entity.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 轮播图添加请求
 * @Author: kris
 * @Create: 2024-05-26 18:48
 **/

@Data
@NoArgsConstructor
public class CarouselAddReq {
    String description;
    String link;
}
