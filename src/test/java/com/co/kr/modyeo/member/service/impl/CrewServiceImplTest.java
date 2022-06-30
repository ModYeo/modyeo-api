package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.repository.CrewCategoryRepository;
import com.co.kr.modyeo.member.repository.CrewRepository;
import com.co.kr.modyeo.member.service.CrewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CrewServiceImplTest {

    private CrewService crewService;

    @Mock
    private CrewRepository crewRepository;

    @Mock
    private CrewCategoryRepository crewCategoryRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        crewService = new CrewServiceImpl(crewRepository,crewCategoryRepository);
    }

    private CrewRequest.CategoryDto categoryDto = CrewRequest.CategoryDto.builder()
            .id(1L)
            .name("test category")
            .build();

    private CrewRequest crewCreateRequest = CrewRequest.builder()
            .name("test crew")
            .categoryDtoList(new ArrayList<>(List.of(categoryDto)))
            .build();

    @Test
    @DisplayName("crew 생성 테스트1")
    void crewCreate() {
        Crew crew = crewCreateRequest.toEntity();
        CrewCategory crewCategory = CrewCategory.of()
                .id(1L)
                .crew(crew)
                .category(categoryDto.toEntity())
                .build();

        given(crewRepository.save(any())).willReturn(crew);
        given(crewCategoryRepository.save(any())).willReturn(crewCategory);

        crew = crewRepository.save(crew);

        if(!crewCreateRequest.getCategoryDtoList().isEmpty()) {
            for (CrewRequest.CategoryDto categoryDto : crewCreateRequest.getCategoryDtoList()) {
                Category category = categoryDto.toEntity();
                CrewCategory crewCategory1 = CrewCategory.of()
                        .crew(crew)
                        .category(category)
                        .build();
                crewCategoryRepository.save(crewCategory1);
            }
        }
    }

    @Test
    @DisplayName("crew 생성 테스트2")
    void crewCreate2() {
        Crew crew = crewCreateRequest.toEntity();
        CrewCategory crewCategory = CrewCategory.of()
                .id(1L)
                .crew(crew)
                .category(categoryDto.toEntity())
                .build();

        given(crewRepository.save(any())).willReturn(crew);
        given(crewCategoryRepository.save(any())).willReturn(crewCategory);
        crew = crewService.createCrew(crewCreateRequest);

        then(crewRepository).should().save(any());
        then(crewCategoryRepository).should().save(any());
    }
}