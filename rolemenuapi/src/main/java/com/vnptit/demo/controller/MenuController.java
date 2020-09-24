package com.vnptit.demo.controller;

import com.vnptit.demo.dto.MenuDto;
import com.vnptit.demo.entity.Menu;
import com.vnptit.demo.entity.Role;
import com.vnptit.demo.exception.DuplicateException;
import com.vnptit.demo.exception.ValidateException;
import com.vnptit.demo.response.MenuConvert;
import com.vnptit.demo.response.MessageResponse;
import com.vnptit.demo.service.IMenuService;
import com.vnptit.demo.service.IRoleService;
import com.vnptit.demo.util.FunctionHelper;
import com.vnptit.demo.util.Status;
import com.vnptit.demo.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/roles")
public class MenuController {

  @Autowired
  private IRoleService roleService;

  @Autowired
  private IMenuService menuService;

  public MessageResponse<?> messageResponse = new MessageResponse<>();

  /**
   * Check role valid or invalid to access database
   *
   * @param role
   * @return int role
   * @throws NullPointerException
   */
  public int checkRole(Role role) throws NullPointerException {
    int roleInt = 0;
    if (role.getRoleStatus().equals("ACTIVE")) {
      switch (role.getRoleName()) {
        case "ADMIN":
          return roleInt + 1;
        case "USER":
          return roleInt + 2;
        case "MANAGER":
          return roleInt + 3;
        default:
          return roleInt;
      }
    }
    return roleInt;
  }

  /**
   * set messageResponse to client
   *
   * @param messageResponse
   * @param statusCode
   * @param status
   * @param message
   * @return object messageReponse
   */
  public MessageResponse<?> setMessageResponse(MessageResponse messageResponse, StatusCode statusCode, Status status, String message) {
    messageResponse.setCode(statusCode);
    messageResponse.setStatus(status);
    messageResponse.setMessage(message);
    return messageResponse;
  }


  @GetMapping("/")
  public ResponseEntity<?> getAllRole(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "limit", defaultValue = "5") int limit) {
    return ResponseEntity.status(HttpStatus.OK).body(roleService.getListsRole(page, limit));
  }

  @PostMapping("/")
  public ResponseEntity<?> createRole(@RequestBody Role role) {
    if (role.getRoleName().length() > 10) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(setMessageResponse(messageResponse, StatusCode.ERR_102, Status.FAILED, "Length menu name max is 10"));
    } else {
      return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(role));
    }
  }

  @GetMapping("/{roleId}/menus")
  public ResponseEntity<?> getAllMenuByRole(@PathVariable("roleId") String _roleId,
                                            @RequestParam(value = "search", defaultValue = " ") String _keyword,
                                            @RequestParam(value = "page", defaultValue = "0") String _page,
                                            @RequestParam(value = "limit", defaultValue = "5") String _limit) {
    Integer page = FunctionHelper.convertToInt(_page);
    Integer limit = FunctionHelper.convertToInt(_limit);
    Long roleId = FunctionHelper.convertToLong(_roleId);
    Role role = roleService.getRoleById(roleId);
    if (checkRole(role) == 0) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
              .body(setMessageResponse(messageResponse, StatusCode.ERR_099, Status.FAILED, "Role invalid"));
    }
    if (!FunctionHelper.regexString(_keyword)) throw new ValidateException("param invalid");
    return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenuByRoleId(roleId, page, limit, _keyword.trim()));
  }

  @GetMapping("/{roleId}/menus/{menuId}")
  public ResponseEntity<?> getMenuById(@PathVariable("roleId") String _roleId, @PathVariable("menuId") String _menuId) {
    Long roleId = FunctionHelper.convertToLong(_roleId);
    Long menuId = FunctionHelper.convertToLong(_menuId);
    Role role = roleService.getRoleById(roleId);
    if (checkRole(role) == 1) {
      return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenuById(menuId));
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
              .body(setMessageResponse(messageResponse, StatusCode.ERR_099, Status.FAILED, "Role invalid"));
    }
  }

  @PostMapping("/{roleId}/menus")
  public ResponseEntity<?> createMenu(@Valid @PathVariable("roleId") String _roleId,
                                      @RequestBody MenuDto menuDtoRequest) throws Exception {
    Long roleId = FunctionHelper.convertToLong(_roleId);
    Role role = roleService.getRoleById(roleId);

    if (checkRole(role) == 1) {
      // check field in role is not null or null
      if (!FunctionHelper.checkSpace(menuDtoRequest.getMenuName()) || !FunctionHelper.regexString(menuDtoRequest.getMenuName()))
        throw new ValidateException("menu name invalid");
      if (!FunctionHelper.checkSpace(menuDtoRequest.getMenuSlug()) || !FunctionHelper.regexUrl(menuDtoRequest.getMenuSlug()))
        throw new ValidateException("menu slug invalid");
      if (!FunctionHelper.checkSpace(menuDtoRequest.getMenuStatus()) || !FunctionHelper.checkStatus(menuDtoRequest.getMenuStatus()))
        throw new ValidateException("menu status invalid");
      if (menuDtoRequest.getMenuIcon() != null && !FunctionHelper.regexIcon(menuDtoRequest.getMenuIcon()))
        throw new ValidateException("menu icon invalid");
      if (menuDtoRequest.getMenuParentId() == null || FunctionHelper.convertToLong(menuDtoRequest.getMenuParentId()) < 0)
        throw new ValidateException("menu parent id invalid");
      if (menuDtoRequest.getMenuName().length() > 10) throw new ValidateException("menu name max length 10");
      if (menuDtoRequest.getMenuDescription() != null && !FunctionHelper.regexString(menuDtoRequest.getMenuDescription()))
        throw new ValidateException("menu description invalid");
      Menu _menu = MenuConvert.toMenu(menuDtoRequest);
      role.getMenus().add(_menu);
      return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(_menu));
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
              .body(setMessageResponse(messageResponse, StatusCode.ERR_099, Status.FAILED, "Role invalid"));
    }
  }

  @PutMapping("/{roleId}/menus/{menuId}")
  public ResponseEntity<?> updateMenu(@Valid @PathVariable("roleId") String _roleId,
                                      @PathVariable("menuId") String _menuId,
                                      @RequestBody MenuDto menuDtoRequest) throws DuplicateException {
    Long roleId = FunctionHelper.convertToLong(_roleId);
    Long menuId = FunctionHelper.convertToLong(_menuId);
    Long parentId = FunctionHelper.convertToLong(menuDtoRequest.getMenuParentId());
    Role role = roleService.getRoleById(roleId);

    // check field in role is not null or null
    if (checkRole(role) == 1) {
      if (!FunctionHelper.checkSpace(menuDtoRequest.getMenuName()) || !FunctionHelper.regexString(menuDtoRequest.getMenuName()))
        throw new ValidateException("menu name invalid");
      if (!FunctionHelper.checkSpace(menuDtoRequest.getMenuSlug()) || !FunctionHelper.regexUrl(menuDtoRequest.getMenuSlug()))
        throw new ValidateException("menu slug invalid");
      if (!FunctionHelper.checkSpace(menuDtoRequest.getMenuStatus()) || !FunctionHelper.checkStatus(menuDtoRequest.getMenuStatus()))
        throw new ValidateException("menu status invalid");
      if (menuDtoRequest.getMenuIcon() != null && !FunctionHelper.regexIcon(menuDtoRequest.getMenuIcon()))
        throw new ValidateException("menu icon invalid");
      if (menuDtoRequest.getMenuParentId() == null || FunctionHelper.convertToLong(menuDtoRequest.getMenuParentId()) < 0)
        throw new ValidateException("menu parent id invalid");
      if (menuDtoRequest.getMenuName().length() > 10) throw new ValidateException("menu name max length 10");
      if (menuDtoRequest.getMenuDescription() != null && !FunctionHelper.regexString(menuDtoRequest.getMenuDescription()))
        throw new ValidateException("menu description invalid");
      Menu menu = MenuConvert.toMenu(menuDtoRequest);
      if (menu.getMenuParentId() != 0) {
        menuService.getMenuById(menu.getMenuParentId());
      } else {
        menuService.updateMenu(menuId, menu);
      }
      return ResponseEntity.status(HttpStatus.OK).body(menuService.updateMenu(menuId, menu));
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
              .body(setMessageResponse(messageResponse, StatusCode.ERR_099, Status.FAILED, "Role invalid"));
    }
  }

  @DeleteMapping("/{roleId}/menus/{menuId}")
  public ResponseEntity<?> deleteMenu(@PathVariable("roleId") String _roleId,
                                      @PathVariable("menuId") String _menuId) {
    Long roleId = FunctionHelper.convertToLong(_roleId);
    Long menuId = FunctionHelper.convertToLong(_menuId);
    Role role = roleService.getRoleById(roleId);
    if (checkRole(role) == 1) {
      return ResponseEntity.status(HttpStatus.OK).body(menuService.deleteMenu(menuId));
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
              .body(setMessageResponse(messageResponse, StatusCode.ERR_099, Status.FAILED, "Role invalid"));
    }
  }
}
