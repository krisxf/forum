package com.kris.acg.service.rbac;

import com.kris.acg.entity.rbac.Role;

import java.util.List;

/**
 * @author Kristin
 */
public interface RoleService {

    /**
     * 根据用户id查询他所代表的角色
     * @param id 角色id
     * @return 返回角色
     */
    List<Role> selectRoleById(Long id);
}
