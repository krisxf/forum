package com.kris.acg.mapper;

import com.kris.acg.entity.rbac.Role;
import com.kris.acg.entity.rbac.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 根据用户id查询角色信息
     * @param userId 用户id
     * @return 返回角色
     */
    List<UserRole> selectRoleByUserId(Long userId);
}
