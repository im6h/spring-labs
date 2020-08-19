package com.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  public User() {
  }

  public User(long id, String firstname, String lastname) {
    this.id = id;
    this.firstName = firstname;
    this.lastName = lastname;
  }

  public User(String firstname, String lastname) {
    this.firstName = firstname;
    this.lastName = lastname;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
