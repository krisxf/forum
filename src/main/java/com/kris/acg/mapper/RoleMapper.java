package com.kris.acg.mapper;

import com.kris.acg.entity.rbac.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Kristin
 */
@Mapper
public interface RoleMapper {

    /**
     * 根据用户id查询他所代表的角色
     * @param id 角色id
     * @return 返回角色
     */
    List<Role> selectRoleById(Long id);
}
