package com.ngocnv.springtemplate.controller;


import com.ngocnv.springtemplate.entity.Account;
import com.ngocnv.springtemplate.entity.RestResponse;
import com.ngocnv.springtemplate.exceptions.RestError;
import com.ngocnv.springtemplate.service.AccountService;
import javax.security.auth.login.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {


  private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
  @Autowired
  private AccountService accountService;

  @GetMapping("/api/hello")
  public ResponseEntity<String> hello() {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    String msg = String.format("Hello %s", name);
    return new ResponseEntity<>(msg, HttpStatus.OK);
  }

  @GetMapping(path = "/api/me", produces = "application/json")
  public Account me() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return accountService.findAccountByUsername(username);
  }

  @PostMapping(path = "/api/register", produces = "application/json")
  public ResponseEntity<RestError> register(@RequestBody Account account) {
    try {
      account.grantAuthority("ROLE_USER");
      return new ResponseEntity(
          accountService.register(account), HttpStatus.OK);
    } catch (AccountException e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(new RestError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @PreAuthorize("hasRole('USER')")
  @DeleteMapping(path = "/api/user/remove", produces = "application/json")
  public ResponseEntity removeUser() {
    try {
      accountService.removeAuthenticatedAccount();
      return new ResponseEntity(new RestResponse("User removed."), HttpStatus.OK);
    } catch (UsernameNotFoundException e) {
      LOGGER.error(e.getMessage(), e);
      return new ResponseEntity(new RestError(e.getMessage()), HttpStatus.OK);
    }
  }

}
