package com.vnptit.demo.service;

import com.vnptit.demo.dto.MenuDto;
import com.vnptit.demo.entity.Menu;
import com.vnptit.demo.exception.DuplicateException;
import com.vnptit.demo.exception.ValidateException;
import com.vnptit.demo.response.MessageResponse;

import java.util.List;

public interface IMenuService {
  MessageResponse<?> getAllMenu();

  MessageResponse<?> createMenu(Menu menu) throws DuplicateException;

  MessageResponse<?> updateMenu(Long id, Menu menu) throws DuplicateException;

  MessageResponse<?> deleteMenu(Long id);

  MessageResponse<?> getMenuById(Long id);

  MessageResponse<List<MenuDto>> getMenuByRoleId(Long id, int offset, int limit, String search);

  MessageResponse<List<MenuDto>> searchMenu(String keyword);

}
