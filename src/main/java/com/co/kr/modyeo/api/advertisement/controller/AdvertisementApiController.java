package com.co.kr.modyeo.api.advertisement.controller;

import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertisement")
@RequiredArgsConstructor
public class AdvertisementApiController {

    private final AdvertisementService advertisementService;
}
