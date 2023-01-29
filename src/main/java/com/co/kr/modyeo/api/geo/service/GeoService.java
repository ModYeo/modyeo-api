package com.co.kr.modyeo.api.geo.service;

import com.co.kr.modyeo.api.geo.domain.dto.response.SidoAreaResponse;

import java.util.List;

public interface GeoService {
    List<SidoAreaResponse> getGeoInfo();
}
