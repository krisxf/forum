package com.kris.acg.entity.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Program: acg
 * @Description: 分类修改请求封装类
 * @Author: kris
 * @Create: 2024-05-29 21:50
 **/

@Data
@NoArgsConstructor
public class CategoryModifyReq {
    Integer id;
    String label;
}
