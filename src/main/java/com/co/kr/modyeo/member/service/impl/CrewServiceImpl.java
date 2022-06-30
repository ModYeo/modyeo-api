package com.co.kr.modyeo.member.service.impl;

import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.CategoryErrorCode;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
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
        overlapCrewCheck(crewRequest);
        Crew crew = crewRequest.toEntity();
        crew = crewRepository.save(crew);

        if(!crewRequest.getCategoryDtoList().isEmpty()){
            List<Category> categories = crewRequest
                    .getCategoryDtoList().stream()
                    .map(CrewRequest.CategoryDto::toEntity)
                    .collect(Collectors.toList());

            Crew finalCrew = crew;
            List<CrewCategory> crewCategories = categories.stream().map(category -> CrewCategory.of()
                    .category(category)
                    .crew(finalCrew)
                    .build()).collect(Collectors.toList());

            crewCategoryRepository.saveAll(crewCategories);
        }

        return crew;
    }

    @Override
    public Slice<CrewResponse> getCrew(CrewSearch crewSearch) {
        PageRequest page = PageRequest.of(crewSearch.getOffset(), crewSearch.getLimit(), crewSearch.getDirection(),crewSearch.getOrderBy());
        Slice<Crew> crewList = crewRepository.searchCrew(crewSearch,page);
        return crewList.map(CrewResponse::toRes);
    }

    @Override
    public Crew updateCrew(CrewRequest crewRequest) {
        overlapCrewCheck(crewRequest);

        return null;
    }

    private void overlapCrewCheck(CrewRequest crewRequest){
        Crew findCrew = crewRepository.findByName(crewRequest.getName());
        if (findCrew != null){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode(CategoryErrorCode.OVERLAP_CATEGORY.getCode())
                    .errorMessage(CategoryErrorCode.OVERLAP_CATEGORY.getMessage())
                    .build();

        }
    }
}
