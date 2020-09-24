package com.vnptit.demo.repository;

import com.vnptit.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  @Query(value = "SELECT * FROM roles WHERE roles.id = :id", nativeQuery = true)
  Role getRoleById(@Param("id")Long id);


  @Query(value = "SELECT * FROM roles JOIN menu_role JOIN menus ON menus.id = menu_role.menu_id ON roles.id = menu_role.role_id WHERE menus.id = :id", nativeQuery = true)
  List<Role> getRolesByMenuId(@Param("id") Long id);

}


