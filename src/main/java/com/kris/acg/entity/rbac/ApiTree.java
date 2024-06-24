package com.kris.acg.entity.rbac;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-06 14:55
 **/

@NoArgsConstructor
@Data
public class ApiTree {
    private List<Api> apiList=new ArrayList<>();
    private ConcurrentMap<String, ApiTree> children;
}
