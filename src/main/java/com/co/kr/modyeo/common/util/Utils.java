package com.co.kr.modyeo.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Utils {

    public static LocalDateTime changeLongToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                TimeZone.getDefault().toZoneId());
    }
}
