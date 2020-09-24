package com.demo.controller;

import com.demo.dao.UserRepository;
import com.demo.model.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserController {
  private UserRepository users;

  public UserController() {

  }

  public UserController(UserRepository users) {
    this.users = users;
  }

  @POST
  @Path("/")
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  public Response addUser(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
    User user = new User(firstName, lastName);
    users.createUser(user);
    return Response.accepted().build();
  }

  @GET
  @Path("/{id}")
  @Produces({"application/json"})
  public Response getUser(@PathParam("id") long id) {
    User user = users.findUserById(id);
    if (user != null) {
      return Response.status(Response.Status.ACCEPTED).entity(user).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @GET
  @Path("/")
  @Produces({"application/json"})
  public Response getUsers() {
    return Response.status(Response.Status.ACCEPTED).entity(users.getAllUser()).build();
  }

  @DELETE
  @Path("/{id}")
  public Response removeUser(@PathParam("id") long id) {
    users.removeUserById(id);
    return Response.status(Response.Status.OK).build();
  }

  @PUT
  @Path("/{id}")
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  public Response testFormParam(@PathParam("id") long id, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
    User userFinded = users.findUserById(id);
    if (userFinded != null) {
      userFinded.setFirstName(firstName);
      userFinded.setLastName(lastName);
      users.updateUser(userFinded);
    }
    return Response.status(Response.Status.OK).build();
  }

}
