package com.kris.acg.mapper;

import com.kris.acg.entity.rbac.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Kristin
 */
@Mapper
public interface PermissionMapper {

    /**
     * 查询权限信息
     * @param id 权限id
     * @return 返回
     */
    Permission queryPermissionById(Integer id);
}
