package com.ngocnv.springtemplate;

import com.ngocnv.springtemplate.entity.Account;
import com.ngocnv.springtemplate.service.AccountService;
import java.util.Arrays;
import javax.security.auth.login.AccountException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTemplateApplication.class, args);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner init(
      AccountService accountService
  ) {
    return (evt) -> Arrays.asList(
        "user,admin,john,robert,ana".split(",")).forEach(
        username -> {
          Account acct = new Account();
          acct.setUsername(username);
          if (username.equals("user")) {
            acct.setPassword("password");
          } else {
            acct.setPassword(passwordEncoder().encode("password"));
          }
          acct.setFirstName(username);
          acct.setLastName("LastName");
          acct.grantAuthority("ROLE_USER");
          if (username.equals("admin")) {
            acct.grantAuthority("ROLE_ADMIN");
          }
          try {
            accountService.register(acct);
          } catch (AccountException e) {
            e.printStackTrace();
          }
        }
    );
  }
}
