package com.vnptit.demo.service;

import com.vnptit.demo.dto.RoleDto;
import com.vnptit.demo.entity.Role;
import com.vnptit.demo.response.MessageResponse;

import java.util.List;


public interface IRoleService {

  MessageResponse<?> createRole(Role role);

  Role getRoleById(Long id);

  MessageResponse<List<RoleDto>> getListsRole(int page, int limit);
}
