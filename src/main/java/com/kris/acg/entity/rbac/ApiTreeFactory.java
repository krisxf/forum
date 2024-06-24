package com.kris.acg.entity.rbac;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-06 14:55
 **/

@Component
@Data
public class ApiTreeFactory {
    private ApiTree prefixMatchApiTree;
    private ApiTree exactMatchApiTree;
    private ApiTree apiTree;
}
