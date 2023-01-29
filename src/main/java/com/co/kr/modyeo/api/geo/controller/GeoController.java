package com.co.kr.modyeo.api.geo.controller;

import com.co.kr.modyeo.api.geo.domain.dto.response.SidoAreaResponse;
import com.co.kr.modyeo.api.geo.service.GeoService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geo")
@RequiredArgsConstructor
public class GeoController {

    private final GeoService geoService;

    @GetMapping("")
    public ResponseEntity<?> getGeoInfo(){
        List<SidoAreaResponse> sidoAreaResponseList = geoService.getGeoInfo();
        return ResponseHandler.generate()
                .data(sidoAreaResponseList)
                .status(HttpStatus.OK)
                .build();
    }
}
