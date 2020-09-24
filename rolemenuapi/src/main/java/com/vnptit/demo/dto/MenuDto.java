package com.vnptit.demo.dto;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MenuDto {
  private Long id;
  @NotNull
  @NotEmpty(message = "Please provide menu name")
  @ApiModelProperty(required = true, value = "ten cua menu")
  private String menuName;

  @ApiModelProperty(required = true, value = "mo ta cua menu")
  private String menuDescription;

  @NotNull
  @NotEmpty(message = "Please provide menu slug")
  @ApiModelProperty(required = true, value = "duong dan cua menu")
  private String menuSlug;

  @NotNull
  @ApiModelProperty(required = true, value = "id parent cua menu")
  private String menuParentId;

  @Column(name = "menuIcon", length = 10)
  @ApiModelProperty(required = true, value = "mo ta duong dan co dang [http, www, /slug]")
  private String menuIcon;

  @NotEmpty(message = "Please provide menu level")
  @ApiModelProperty(required = true, value = "menu name")
  private String menuLevel;


  @NotNull
  @NotEmpty(message = "Please provide menu status")
  @ApiModelProperty(required = true, value = "trang thai cua menu co dang [ACTIVE, INACTIVE, DISABLE]")
  private String menuStatus;

  public MenuDto() {
  }

  public MenuDto(Long id, String menuName, String menuLevel, String menuDescription, String menuSlug, String menuParentId, String menuIcon, String menuStatus) {
    this.id = id;
    this.menuName = menuName;
    this.menuLevel = menuLevel;
    this.menuDescription = menuDescription;
    this.menuSlug = menuSlug;
    this.menuParentId = menuParentId;
    this.menuIcon = menuIcon;
    this.menuStatus = menuStatus;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public String getMenuLevel() {
    return menuLevel;
  }

  public void setMenuLevel(String menuLevel) {
    this.menuLevel = menuLevel;
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

  public String getMenuParentId() {
    return menuParentId;
  }

  public void setMenuParentId(String menuParentId) {
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

}
