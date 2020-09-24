package com.vnptit.demo.repository;

import com.vnptit.demo.entity.Menu;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
  @Query(value = "SELECT * FROM menus m WHERE m.menu_parent_id = :id", nativeQuery = true)
  List<Menu> getMenuByMenuParentId(@Param("id") Long id);


  @Query(value = "SELECT * FROM menus JOIN menu_role JOIN roles WHERE roles.id = :id AND roles.id = menu_role.role_id AND menus.id = menu_role.menu_id AND menus.menu_status <> 'DISABLE' AND menus.menu_name LIKE :keyword ", nativeQuery = true)
  List<Menu> getMenusByRoleId(@Param("id") Long id, @Param("keyword") String search, Pageable pageable);

  @Query(value = "SELECT  * FROM menus WHERE menus.menu_name LIKE :keyword", nativeQuery = true)
  List<Menu> searchMenu(@Param("keyword") String keywork);

  @Transactional
  @Modifying
  @Query(value = "UPDATE menus SET menu_status = :status WHERE menu_parent_id = :id", nativeQuery = true)
  void changeStatusChildMenu(@Param("id")Long id,@Param("status") String status);
}
