package com.co.kr.modyeo.api.geo.service.impl;

import com.co.kr.modyeo.api.geo.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GeoServiceImpl implements GeoService {
}
