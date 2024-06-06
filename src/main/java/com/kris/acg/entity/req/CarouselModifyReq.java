package com.kris.acg.entity.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 修改轮播图请求类
 * @Author: kris
 * @Create: 2024-05-26 21:09
 **/

@Data
@NoArgsConstructor
public class CarouselModifyReq {
    private Long id;
    private String description;
    private String link;
}
