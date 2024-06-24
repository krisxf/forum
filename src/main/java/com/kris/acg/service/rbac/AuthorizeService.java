package com.kris.acg.service.rbac;

import com.kris.acg.entity.rbac.Permission;

import java.util.List;

/**
 * @author Kristin
 */
public interface AuthorizeService {
    /**
     * 查询角色所拥有的权限
     * @param roleId 角色id
     * @return 返回权限
     */
    List<Permission> queryPermissionByRoleId(Integer roleId);
}
