package com.co.kr.modyeo.api.geo.service.impl;

import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;
import com.co.kr.modyeo.api.geo.domain.dto.response.SidoAreaResponse;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.domain.entity.SidoArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.geo.repository.SidoAreaRepository;
import com.co.kr.modyeo.api.geo.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GeoServiceImpl implements GeoService {

    private final SidoAreaRepository sidoAreaRepository;

    private final EmdAreaRepository emdAreaRepository;

    @Override
    public List<SidoAreaResponse> getGeoInfo() {
        return sidoAreaRepository.findAll().stream()
                .map(SidoAreaResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmdAreaDetail> getGeoInfoByEmdName(String name) {
        return emdAreaRepository.findByNameContains(name);
    }
}
