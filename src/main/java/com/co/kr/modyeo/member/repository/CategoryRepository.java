package com.co.kr.modyeo.member.repository;

import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.repository.custom.CategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> , CategoryCustomRepository {
   Category findByName(String name);
}
