package com.finance.personal.repository;

import com.finance.personal.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    public List<TransactionEntity> findAllByAccountId(Long accountId);
}
