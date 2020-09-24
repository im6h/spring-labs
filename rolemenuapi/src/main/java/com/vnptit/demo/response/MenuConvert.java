package com.vnptit.demo.response;

import com.vnptit.demo.dto.MenuDto;
import com.vnptit.demo.entity.Menu;

public class MenuConvert {
  public static MenuDto toMenuDto(Menu menu) {
    MenuDto menuDto = new MenuDto();
    menuDto.setId(menu.getId());
    menuDto.setMenuDescription(menu.getMenuDescription());
    menuDto.setMenuIcon(menu.getMenuIcon());
    menuDto.setMenuName(menu.getMenuName());
    menuDto.setMenuSlug(menu.getMenuSlug());
    menuDto.setMenuLevel(menu.getMenuLevel());
    menuDto.setMenuParentId(String.valueOf(menu.getMenuParentId()));
    menuDto.setMenuStatus(menu.getMenuStatus().toUpperCase());
    return menuDto;
  }

  public static Menu toMenu(MenuDto menuDto) {
    Menu menu = new Menu();
    menu.setMenuSlug(menuDto.getMenuSlug());
    if (menuDto.getMenuDescription() != null) menu.setMenuDescription(menuDto.getMenuDescription().trim());
    menu.setMenuName(menuDto.getMenuName().trim());
    menu.setMenuParentId(Long.valueOf(menuDto.getMenuParentId()));
    menu.setMenuIcon(menuDto.getMenuIcon());
    menu.setMenuStatus(menuDto.getMenuStatus().toUpperCase());
    menu.setMenuLevel(menuDto.getMenuLevel());
    return menu;
  }
}
