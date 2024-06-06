package com.kris.acg.entity.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-04 17:14
 **/

@Data
@NoArgsConstructor
public class TagModifyReq {
    Integer id;
    String label;
}
