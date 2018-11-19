package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.User;
import com.ngocnv.springtemplate.validate.In;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@RestController
@Validated
public class UserController {

  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    return user;
  }

  @GetMapping("/users")
  public List<User> getUsers(@Valid @In(list = {"male", "female"}) @RequestParam("type") String type) {
    return Collections.emptyList();
  }
}
