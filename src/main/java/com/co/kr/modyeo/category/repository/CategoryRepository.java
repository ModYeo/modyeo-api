package com.co.kr.modyeo.category.repository;

import com.co.kr.modyeo.category.domain.entity.Category;
import com.co.kr.modyeo.category.repository.custom.CategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> , CategoryCustomRepository {
   Category findByName(String name);
}
