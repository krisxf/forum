package com.kris.acg.entity.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-04 19:48
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendCode {
    String email;
    String msg;
    String code;
}
