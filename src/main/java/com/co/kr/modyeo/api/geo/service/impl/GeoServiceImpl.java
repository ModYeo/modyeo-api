package com.co.kr.modyeo.api.geo.service.impl;

import com.co.kr.modyeo.api.geo.domain.dto.response.SidoAreaResponse;
import com.co.kr.modyeo.api.geo.domain.entity.SidoArea;
import com.co.kr.modyeo.api.geo.repository.SidoAreaRepository;
import com.co.kr.modyeo.api.geo.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    private final SidoAreaRepository sidoAreaRepository;

    @Override
    public List<SidoArea> getSidoInfo() {
        sidoAreaRepository.findAll().stream().map(SidoAreaResponse)
        return null;
    }
}
