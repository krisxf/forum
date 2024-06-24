package com.kris.acg.mapper;

import com.kris.acg.entity.rbac.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface AuthorizeMapper {


    /**
     * 查询角色所拥有的权限
     * @param roleId 角色id
     * @return 返回权限
     */
    List<Permission> selectPermissionByRoleId(Integer roleId);
}
