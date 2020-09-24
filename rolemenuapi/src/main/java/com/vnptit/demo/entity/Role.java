package com.vnptit.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements Serializable {

  @Column(name = "roleName", unique = true)
  private String roleName;

  @Column(name = "roleDescription")
  private String roleDescription;

  @Column(name = "roleStatus")
  private String roleStatus;

  @ManyToMany
  @JoinTable(name = "menu_role",
          joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id")
  )
  private List<Menu> menus = new ArrayList<>();

  public Role() {
  }

  public Role(String roleName, String roleDescription, String roleStatus, List<Menu> menus) {
    this.roleName = roleName;
    this.roleDescription = roleDescription;
    this.roleStatus = roleStatus;
    this.menus = menus;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

  public String getRoleStatus() {
    return roleStatus;
  }

  public void setRoleStatus(String roleStatus) {
    this.roleStatus = roleStatus;
  }

  public List<Menu> getMenus() {
    return menus;
  }

  public void setMenus(List<Menu> menus) {
    this.menus = menus;
  }
}

