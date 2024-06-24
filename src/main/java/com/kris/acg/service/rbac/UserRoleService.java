package com.kris.acg.service.rbac;

import com.kris.acg.entity.rbac.Role;
import com.kris.acg.entity.rbac.UserRole;

import java.util.List;

/**
 * @author Kristin
 */
public interface UserRoleService {

    /**
     * 根据用户id查询角色信息
     * @param userId 用户id
     * @return 返回角色
     */
    List<UserRole> queryRoleByUserId(Long userId);
}
