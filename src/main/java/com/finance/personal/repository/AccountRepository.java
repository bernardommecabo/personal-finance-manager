package com.finance.personal.repository;

import com.finance.personal.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Long> {
    public AccountEntity findByName(String name);
    public List<AccountEntity> findAllByUserId(Long userId);
}
