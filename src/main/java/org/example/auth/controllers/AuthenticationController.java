package org.example.auth.controllers;

import org.example.services.UserService;

import org.example.models.User;

import jakarta.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/auth")
public class AuthenticationController {

    @Inject
    private UserService userService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String register(User user) {
        return userService.register(user.getUsername(), user.getPassword(), user.getRole());
    }
}
