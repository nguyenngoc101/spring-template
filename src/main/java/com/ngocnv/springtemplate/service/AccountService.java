package com.ngocnv.springtemplate.service;

import com.ngocnv.springtemplate.entity.Account;
import com.ngocnv.springtemplate.repo.AccountRepo;
import java.util.Optional;
import javax.security.auth.login.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("accountService")
public class AccountService implements UserDetailsService {

  @Autowired
  private AccountRepo accountRepo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String s) {
    Optional<Account> account = accountRepo.findByUsername(s);
    if (account.isPresent()) {
      return account.get();
    } else {
      throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
    }
  }

  public Account findAccountByUsername(String username) {
    Optional<Account> account = accountRepo.findByUsername(username);
    if (account.isPresent()) {
      return account.get();
    } else {
      throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
    }

  }

  public Account register(Account account) throws AccountException {
    if (accountRepo.countByUsername(account.getUsername()) == 0) {
      account.setPassword(passwordEncoder.encode(account.getPassword()));
      return accountRepo.save(account);
    } else {
      throw new AccountException(
          String.format("Username[%s] already taken.", account.getUsername()));
    }
  }

  @Transactional // To successfully remove the date @Transactional annotation must be added
  public void removeAuthenticatedAccount() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Account acct = findAccountByUsername(username);
    accountRepo.deleteAccountById(acct.getId());

  }
}
