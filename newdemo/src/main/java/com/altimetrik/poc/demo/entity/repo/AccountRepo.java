package com.altimetrik.poc.demo.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrik.poc.demo.entity.AccountEntity;
import com.altimetrik.poc.demo.entity.AccountPK;

public interface AccountRepo extends JpaRepository<AccountEntity, AccountPK> {
}
