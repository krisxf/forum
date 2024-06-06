package com.kris.acg.entity.admin;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description: 网站数据
 * @Author: kris
 * @Create: 2024-05-26 17:12
 **/

@Data
@NoArgsConstructor
public class WebsiteDataVo {
    private Integer pageView;
    private Integer apiAccessCount;
}
