package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
import com.co.kr.modyeo.common.exception.code.CrewErrorCode;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.repository.CategoryRepository;
import com.co.kr.modyeo.member.repository.CrewCategoryRepository;
import com.co.kr.modyeo.member.repository.CrewRepository;
import com.co.kr.modyeo.member.service.CrewCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrewCategoryServiceImpl implements CrewCategoryService {
    private final CrewRepository crewRepository;
    private final CategoryRepository categoryRepository;
    private final CrewCategoryRepository crewCategoryRepository;

    @Override
    public CrewCategory createCrewCategory(Long crewId, Long categoryId) {
        Crew crew = findCrew(crewId);
        Category category = findCategory(categoryId);

        CrewCategory crewCategory = CrewCategory.of()
                .crew(crew)
                .category(category)
                .build();

        crewCategoryRepository.save(crewCategory);
        return crewCategory;
    }

    @Override
    public void deleteCrewCategory(Long crewCategoryId) {
        CrewCategory crewCategory = crewCategoryRepository.findById(crewCategoryId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(null)
                        .errorMessage(null)
                        .build()
        );

        crewCategoryRepository.delete(crewCategory);
    }

    private Crew findCrew(Long crewId){
        return crewRepository.findById(crewId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(CrewErrorCode.NOT_FOUND_CREW.getMessage())
                        .errorCode(CrewErrorCode.NOT_FOUND_CREW.getCode())
                        .build());
    }

    private Category findCategory(Long categoryId){
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(CategoryErrorCode.NOT_FOUND_CATEGORY.getMessage())
                        .errorCode(CategoryErrorCode.NOT_FOUND_CATEGORY.getCode())
                        .build());
    }
}
