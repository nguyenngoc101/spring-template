package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.User;
import com.ngocnv.springtemplate.validate.In;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
    return user;
  }

  @GetMapping("/users")
  public List<User> getUsers(
      @Valid @In(list = {"male", "female"}) @RequestParam("type") String type) {
    return Collections.emptyList();
  }
}
