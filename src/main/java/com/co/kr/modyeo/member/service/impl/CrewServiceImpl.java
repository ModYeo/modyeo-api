package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.repository.CrewCategoryRepository;
import com.co.kr.modyeo.member.repository.CrewRepository;
import com.co.kr.modyeo.member.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrewServiceImpl implements CrewService {

    private final CrewRepository crewRepository;

    private final CrewCategoryRepository crewCategoryRepository;

    @Override
    public Crew crewCreate(CrewRequest crewRequest) {
        Crew crew = crewRequest.toEntity();
        crew = crewRepository.save(crew);

        if(!crewRequest.getCategoryDtoList().isEmpty()){
            Crew finalCrew = crew;
            crewRequest.getCategoryDtoList()
                    .forEach(categoryDto -> {
                        Category category = categoryDto.toEntity();
                        CrewCategory crewCategory = CrewCategory.of()
                                .crew(finalCrew)
                                .category(category)
                                .build();

                        crewCategoryRepository.save(crewCategory);
                    });
        }

        return crew;
    }
}
