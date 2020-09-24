package com.vnptit.demo.service.implement;

import com.vnptit.demo.dto.RoleDto;
import com.vnptit.demo.entity.Role;
import com.vnptit.demo.exception.NotFoundException;
import com.vnptit.demo.exception.ValidateException;
import com.vnptit.demo.repository.RoleRepository;
import com.vnptit.demo.response.MessageResponse;
import com.vnptit.demo.response.RoleResponse;
import com.vnptit.demo.service.IRoleService;
import com.vnptit.demo.util.Status;
import com.vnptit.demo.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
  @Autowired
  private RoleRepository roleRepository;


  @Override
  public MessageResponse<?> createRole(Role role) {
    try {
      roleRepository.save(role);
      MessageResponse<?> messageResponse = new MessageResponse();
      messageResponse.setMessage("Create success");
      messageResponse.setData(null);
      messageResponse.setStatus(Status.SUCCESS);
      messageResponse.setCode(StatusCode.SUCCESS_201);
      return messageResponse;
    } catch (Exception e) {
      throw new ValidateException("Duplicate role name");
    }
  }


  @Override
  public Role getRoleById(Long id) {
    try {
      Role role = roleRepository.getRoleById(id);
      return role;
    } catch (EntityNotFoundException e) {
      throw new NotFoundException("Not found menu with id:" + id);
    }
  }


  @Override
  public MessageResponse<List<RoleDto>> getListsRole(int offset, int limit) {
    List<RoleDto> roleDtoList = new ArrayList<>();
    Page<Role> page = roleRepository.findAll(PageRequest.of(offset, limit));
    for (Role _role : page){
      roleDtoList.add(RoleResponse.toRoleDto(_role));
    }

    MessageResponse<List<RoleDto>> messageResponse = new MessageResponse<List<RoleDto>>();
    messageResponse.setCode(StatusCode.SUCCESS_200);
    messageResponse.setMessage("Get all roles");
    messageResponse.setStatus(Status.SUCCESS);
    messageResponse.setData(roleDtoList);
    return messageResponse;
  }

}
