package com.co.kr.modyeo.api.columncode.controller;

import com.co.kr.modyeo.api.columncode.service.ColumnCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/column-code")
@RequiredArgsConstructor
public class ColumnCodeController {

    private final ColumnCodeService columnCodeService;
}
