package com.kris.acg.service.Imp.rbac;

import com.kris.acg.entity.rbac.Role;
import com.kris.acg.entity.rbac.UserRole;
import com.kris.acg.mapper.UserRoleMapper;
import com.kris.acg.service.rbac.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-23 15:16
 **/

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> queryRoleByUserId(Long userId) {
        return userRoleMapper.selectRoleByUserId(userId);
    }
}
