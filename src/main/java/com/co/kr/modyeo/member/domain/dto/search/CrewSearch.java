package com.co.kr.modyeo.member.domain.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrewSearch {

    private Long crewId;

    private String name;

    private Long categoryId;
}
