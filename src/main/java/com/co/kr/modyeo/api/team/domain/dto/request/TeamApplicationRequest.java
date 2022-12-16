package com.co.kr.modyeo.api.team.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamApplicationRequest {

    private Long memberId;

    private Long teamId;

    private String  introduce;
}
