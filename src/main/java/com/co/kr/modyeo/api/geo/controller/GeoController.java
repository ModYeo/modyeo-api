package com.co.kr.modyeo.api.geo.controller;

import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;
import com.co.kr.modyeo.api.geo.domain.dto.response.SidoAreaResponse;
import com.co.kr.modyeo.api.geo.service.GeoService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geo")
@RequiredArgsConstructor
public class GeoController {

    private final GeoService geoService;

    @ApiOperation(value = "전체 지역 정보 조회")
    @GetMapping("")
    public ResponseEntity<?> getGeoInfo(){
        List<SidoAreaResponse> sidoAreaResponseList = geoService.getGeoInfo();
        return ResponseHandler.generate()
                .data(sidoAreaResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value = "지역(읍면동) 검색 API")
    @GetMapping("/emd")
    public ResponseEntity<?> getGeoInfoByEmdName(
            @RequestParam(value = "name", name = "name", defaultValue = "", required = true) String name){
        List<EmdAreaDetail> emdAreaDetails = geoService.getGeoInfoByEmdName(name);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(emdAreaDetails)
                .build();
    }
}
