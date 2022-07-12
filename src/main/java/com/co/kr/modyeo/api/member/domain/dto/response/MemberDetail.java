package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MemberDetail {

    private Long memberId;

    private String username;

    private Sex sex;

    private List<TeamResponse> teamResponseList;


}
