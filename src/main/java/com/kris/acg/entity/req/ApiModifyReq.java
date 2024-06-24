package com.kris.acg.entity.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-05-28 22:31
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiModifyReq {
    Integer id;

    String path;

    Integer permissionId;

    String note;
    boolean enable;
}
