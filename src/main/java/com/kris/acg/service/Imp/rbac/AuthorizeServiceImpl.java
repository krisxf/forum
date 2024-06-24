package com.kris.acg.service.Imp.rbac;

import com.kris.acg.entity.rbac.Permission;
import com.kris.acg.mapper.AuthorizeMapper;
import com.kris.acg.service.rbac.AuthorizeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-23 14:20
 **/

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Resource
    AuthorizeMapper authorizeMapper;

    @Override
    public List<Permission> queryPermissionByRoleId(Integer roleId) {
        return authorizeMapper.selectPermissionByRoleId(roleId);
    }
}
