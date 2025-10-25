package com.finance.personal.repository;

import com.finance.personal.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    public CategoryEntity findByName(String name);
}
