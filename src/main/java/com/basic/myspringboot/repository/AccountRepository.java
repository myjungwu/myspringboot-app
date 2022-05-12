package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    // username로 조회하는 finder method 선언
    Optional<Account> findByUsername(String username);
}
