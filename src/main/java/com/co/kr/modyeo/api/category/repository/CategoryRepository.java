package com.co.kr.modyeo.api.category.repository;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.custom.CategoryCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> , CategoryCustomRepository {
   Category findByNameAndUseYn(String name, Yn useYn);

   //select * from category where name = ''
}
