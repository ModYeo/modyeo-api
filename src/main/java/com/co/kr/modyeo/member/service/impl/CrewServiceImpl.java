package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Category;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.repository.CrewCategoryRepository;
import com.co.kr.modyeo.member.repository.CrewRepository;
import com.co.kr.modyeo.member.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrewServiceImpl implements CrewService {

    private final CrewRepository crewRepository;

    private final CrewCategoryRepository crewCategoryRepository;

    @Override
    @Transactional
    public Crew createCrew(CrewRequest crewRequest) {
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

    @Override
    public List<CrewResponse> getCrew(CrewSearch crewSearch) {
        List<Crew> crewList = crewCategoryRepository.searchCrew(crewSearch);
        return crewList.stream().map(CrewResponse::toRes).collect(Collectors.toList());
    }
}
