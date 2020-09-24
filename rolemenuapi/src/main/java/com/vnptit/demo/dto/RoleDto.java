package com.vnptit.demo.dto;

public class RoleDto {
  private Long id;
  private String roleName;
  private String roleStatus;

  public RoleDto() {

  }

  public RoleDto(Long id, String roleName, String roleStatus) {
    this.id = id;
    this.roleName = roleName;
    this.roleStatus = roleStatus;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleStatus() {
    return roleStatus;
  }

  public void setRoleStatus(String roleStatus) {
    this.roleStatus = roleStatus;
  }
}
