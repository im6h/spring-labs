package com.vnptit.demo.response;

import com.vnptit.demo.dto.RoleDto;
import com.vnptit.demo.entity.Role;

public class RoleResponse {
  public static RoleDto toRoleDto(Role role){

    RoleDto roleDto = new RoleDto();
    roleDto.setId(role.getId());
    roleDto.setRoleName(role.getRoleName());
    roleDto.setRoleStatus(role.getRoleStatus());

    return roleDto;
  }
}
