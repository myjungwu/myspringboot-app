package com.basic.myspringboot.service;

import com.basic.myspringboot.entity.Account;
import com.basic.myspringboot.property.MybootProperty;
import com.basic.myspringboot.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final MybootProperty property;
    private final PasswordEncoder passwordEncoder;


    public Account createAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    @PostConstruct
    public void init() {
        Optional<Account> byUsername = accountRepository.findByUsername(property.getUsername());
        //username이 없다면
        if(!byUsername.isPresent()) {
            Account newAccount = this.createAccount(property.getUsername(), property.getPassword());
            System.out.println(newAccount);
        }
    }

    @Override
    //login 할때 사용자가 입력한 정보가 유효한지를 체크한다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = accountRepository.findByUsername(username);
        Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(account.getUsername(),
                account.getPassword(), authorities());
    }
    //User 객체의 세번째 인자 USER라는 ROLE을 가진 사용자이다 라고 설정하는 부분
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }



}
