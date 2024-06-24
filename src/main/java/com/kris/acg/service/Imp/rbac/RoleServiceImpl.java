package com.kris.acg.service.Imp.rbac;

import com.kris.acg.entity.rbac.Role;
import com.kris.acg.service.rbac.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-23 14:20
 **/
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> selectRoleById(Long id) {
        return null;
    }
}
