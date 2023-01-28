package com.co.kr.modyeo.api.geo.controller;

import com.co.kr.modyeo.api.geo.service.GeoService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geo")
@RequiredArgsConstructor
public class GeoController {

    private final GeoService geoService;

    @GetMapping("/sido")
    public ResponseEntity<?> getSidoInfo(){
        geoService.getSidoInfo();
        return ResponseHandler.generate()
                .build();
    }
}
