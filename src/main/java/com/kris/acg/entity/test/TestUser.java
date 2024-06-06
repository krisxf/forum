package com.kris.acg.entity.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2023-10-02 15:18
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestUser {
    private String username;
    private Integer age;
}
