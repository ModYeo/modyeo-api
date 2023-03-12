package com.co.kr.modyeo.api.report.domain.enumuerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportReason {

    SPAM("SPAM","스팸");

    private final String code;

    private final String text;
}
