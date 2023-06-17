package com.co.kr.modyeo.api.schedule.domain.entity.enumurate;

import lombok.Getter;

@Getter
public enum DayOfWeekForMySql {
    MONDAY("MONDAY",2),
    TUESDAY("TUESDAY",3),
    WEDNESDAY("WEDNESDAY",4),
    THURSDAY("THURSDAY",5),
    FRIDAY("FRIDAY",6),
    SATURDAY("SATURDAY",7),
    SUNDAY("SUNDAY",1);

    private String code;

    private int value;

    DayOfWeekForMySql(String code, int value) {
        this.code = code;
        this.value = value;
    }
}
