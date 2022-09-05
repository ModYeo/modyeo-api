package com.co.kr.modyeo.api.category.repository;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.custom.CategoryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> , CategoryCustomRepository {
   Category findByName(String name);

   //select * from category where name = ''

   @Query(value = "select c from Category c where c.id in (:ids)")
   List<Category> findByCategoryIds(@Param("ids") List<Long> categoryIds);
}
