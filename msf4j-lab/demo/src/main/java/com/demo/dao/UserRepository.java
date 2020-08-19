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

  public void removeUser(long id) {
    remove(id);
  }

  public User findUser(long id) {
    return find(id);
  }

  public List<User> findUsers() {
    return findAll(User.class);
  }

  public void updateUser(User user) {
    update(user);
  }
}