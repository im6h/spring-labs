package com.vnptit.demo.entity;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
//@Where(clause = "menuStatus != 'DISABLE'")
public class Menu extends BaseEntity implements Serializable {

  @NotNull
  @NotEmpty(message = "Please provide menu name")
  @Column(name = "menuName", unique = true, length = 10)
  private String menuName;

  @Column(name = "menuDescription")
  private String menuDescription;

  @NotNull
  @NotEmpty(message = "Please provide menu slug")
  @Column(name = "menuSlug", length = 10)
  private String menuSlug;

  @NotNull
  @Column(name = "menuParentId", length = 10)
  private Long menuParentId;

  @Column(name = "menuIcon", length = 10)
  @ApiModelProperty(required = true, value = "mo ta duong dan[http, www, /slug]")
  private String menuIcon;

  @Column(name = "menuLevel")
  private String menuLevel;


  @NotNull
  @NotEmpty(message = "Please provide menu status")
  @Column(name = "menuStatus", length = 10)
  private String menuStatus;

  @ManyToMany(mappedBy = "menus")
  private List<Role> roles = new ArrayList<>();

  public Menu() {
  }

  public Menu(String menuName, String menuDescription, String menuSlug, Long menuParentId, String menuIcon,String menuLevel, String menuStatus, List<Role> roles) {
    this.menuName = menuName;
    this.menuDescription = menuDescription;
    this.menuSlug = menuSlug;
    this.menuParentId = menuParentId;
    this.menuIcon = menuIcon;
    this.menuStatus = menuStatus;
    this.roles = roles;
    this.menuLevel = menuLevel;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuDescription() {
    return menuDescription;
  }

  public void setMenuDescription(String menuDescription) {
    this.menuDescription = menuDescription;
  }

  public String getMenuSlug() {
    return menuSlug;
  }

  public void setMenuSlug(String menuSlug) {
    this.menuSlug = menuSlug;
  }

  public Long getMenuParentId() {
    return menuParentId;
  }

  public void setMenuParentId(Long menuParentId) {
    this.menuParentId = menuParentId;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public String getMenuStatus() {
    return menuStatus;
  }

  public void setMenuStatus(String menuStatus) {
    this.menuStatus = menuStatus;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public String getMenuLevel() {
    return menuLevel;
  }

  public void setMenuLevel(String menuLevel) {
    this.menuLevel = menuLevel;
  }
}

