package com.demo.dao;

import com.demo.model.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository extends AbstractRepository<User> {

  public UserRepository(EntityManagerFactory emf) {
    super(emf);
  }

  public void createUser(User user) {
    create(user);
  }

  public void removeUserById(long id) {
    remove(id);
  }

  public User findUserById(long id) {
    return find(id);
  }

  public List<User> getAllUser() {
    return findAll(User.class);
  }

  public void updateUser(User user) {
    update(user);
  }
}