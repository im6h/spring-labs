package com.vnptit.demo.service.implement;

import com.vnptit.demo.dto.MenuDto;
import com.vnptit.demo.entity.Menu;
import com.vnptit.demo.exception.DuplicateException;
import com.vnptit.demo.exception.NotFoundException;
import com.vnptit.demo.repository.MenuRepository;
import com.vnptit.demo.response.MenuConvert;
import com.vnptit.demo.response.MessageResponse;
import com.vnptit.demo.service.IMenuService;
import com.vnptit.demo.util.Status;
import com.vnptit.demo.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService implements IMenuService {

  @Autowired
  private MenuRepository menuRepository;


  @Override
  public MessageResponse<?> getAllMenu() {
    List<MenuDto> menuDtoList = new ArrayList<>();
    List<Menu> menuList = menuRepository.findAll();
    for (Menu menu : menuList) {
      menuDtoList.add(MenuConvert.toMenuDto(menu));
    }

    MessageResponse<List<MenuDto>> messageResponse = new MessageResponse<>();
    messageResponse.setCode(StatusCode.SUCCESS_200);
    messageResponse.setStatus(Status.SUCCESS);
    messageResponse.setData(menuDtoList);
    messageResponse.setMessage("Get all menu");
    return messageResponse;
  }

  @Override
  public MessageResponse<?> createMenu(Menu menu) throws DuplicateException {
    MessageResponse<?> messageResponse = new MessageResponse();
    try {
      if (menu.getMenuParentId() == 0) {
        menuRepository.save(menu);
        messageResponse.setCode(StatusCode.SUCCESS_201);
        messageResponse.setStatus(Status.SUCCESS);
        messageResponse.setMessage("Created menu success");
        messageResponse.setData(null);
        return messageResponse;
      } else {
        Menu _menu = menuRepository.getOne(menu.getMenuParentId());
        if (_menu.getMenuStatus().equals("DISABLE")) {
          throw new EntityNotFoundException("Not found parent id");
        } else {
          menuRepository.save(menu);
          messageResponse.setCode(StatusCode.SUCCESS_201);
          messageResponse.setStatus(Status.SUCCESS);
          messageResponse.setMessage("Created menu success");
          messageResponse.setData(null);
        }
        return messageResponse;
      }
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException("Not found parent id");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new DuplicateException("Duplicate name menu");
    }
  }

  @Override
  public MessageResponse<?> updateMenu(Long id, Menu menu) throws DuplicateException {
    MessageResponse<?> messageResponse = new MessageResponse();
    try {
      Menu menuNeedUpdate = menuRepository.getOne(id);
      menuNeedUpdate.setMenuName(menu.getMenuName());
      menuNeedUpdate.setMenuDescription(menu.getMenuDescription());
      menuNeedUpdate.setMenuIcon(menu.getMenuIcon());
      menuNeedUpdate.setMenuSlug(menu.getMenuSlug());
      menuNeedUpdate.setMenuStatus(menu.getMenuStatus());
      menuNeedUpdate.setMenuParentId(menu.getMenuParentId());
      menuNeedUpdate.setMenuLevel(menu.getMenuLevel());
      menuRepository.save(menuNeedUpdate);

      messageResponse.setCode(StatusCode.SUCCESS_204);
      messageResponse.setStatus(Status.SUCCESS);
      messageResponse.setMessage("Update menu success");
      messageResponse.setData(null);

      // change status child menu
      if (menu.getMenuParentId() != 0 && menu.getMenuStatus().equals("INACTIVE")) {
        menuRepository.changeStatusChildMenu(id, "INACTIVE");
      }
      return messageResponse;
    } catch (EntityNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new DuplicateException("Duplicate name menu");
    }
  }


  @Override
  public MessageResponse<?> deleteMenu(Long id) {
    MessageResponse<?> messageResponse = new MessageResponse();
    try {
      Menu menuNeedUpdate = menuRepository.getOne(id);
      menuNeedUpdate.setMenuStatus("DISABLE");
      menuRepository.changeStatusChildMenu(id, "DISABLE");
      menuRepository.save(menuNeedUpdate);

      messageResponse.setCode(StatusCode.SUCCESS_204);
      messageResponse.setStatus(Status.SUCCESS);
      messageResponse.setMessage("Delete menu success");
      messageResponse.setData(null);
      return messageResponse;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new NotFoundException(e.getMessage());
    }

  }

  @Override
  public MessageResponse<?> getMenuById(Long id) {
    MessageResponse<MenuDto> messageResponse = new MessageResponse();
    try {
      Menu menu = menuRepository.getOne(id);
      if (menu.getMenuStatus().equals("DISABLE")) {
        throw new EntityNotFoundException("Not found menu with id:" + id);
      } else {
        messageResponse.setCode(StatusCode.SUCCESS_200);
        messageResponse.setStatus(Status.SUCCESS);
        messageResponse.setMessage("Get all menu success");
        messageResponse.setData(MenuConvert.toMenuDto(menu));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new NotFoundException("Not found menu with id:" + id);
    }
    return messageResponse;

  }

  @Override
  public MessageResponse<List<MenuDto>> getMenuByRoleId(Long id, int page, int limit, String _search) {
    MessageResponse<List<MenuDto>> messageResponse = new MessageResponse<>();
    String search = "%" + _search + "%";
    List<MenuDto> menuDtoList = new ArrayList<>();
    if (limit < 0 || page < 0) {
      menuRepository.getMenusByRoleId(id, search, Pageable.unpaged()).forEach(menu -> {
        menuDtoList.add(MenuConvert.toMenuDto(menu));
      });
    } else {
      menuRepository.getMenusByRoleId(id, search, PageRequest.of(page, limit)).forEach(menu -> {
        menuDtoList.add(MenuConvert.toMenuDto(menu));
      });
    }
    messageResponse.setCode(StatusCode.SUCCESS_200);
    messageResponse.setStatus(Status.SUCCESS);
    messageResponse.setMessage("Get menus success");
    messageResponse.setData(menuDtoList);
    return messageResponse;
  }

  @Override
  public MessageResponse<List<MenuDto>> searchMenu(String _keyword) {
    List<MenuDto> menuDtoList = new ArrayList<>();
    String keyword = "%" + _keyword + "%";
    System.out.println(keyword);
    menuRepository.searchMenu(keyword).forEach(menu -> {
      menuDtoList.add(MenuConvert.toMenuDto(menu));
    });
    MessageResponse<List<MenuDto>> messageResponse = new MessageResponse();
    messageResponse.setCode(StatusCode.SUCCESS_200);
    messageResponse.setStatus(Status.SUCCESS);
    messageResponse.setMessage("Get menus success");
    messageResponse.setData(menuDtoList);
    return messageResponse;
  }


}
