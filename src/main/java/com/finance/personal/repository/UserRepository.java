package com.finance.personal.repository;

import com.finance.personal.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
    public UserEntity findByName(String name);
    public UserEntity findByEmail(String email);
}
