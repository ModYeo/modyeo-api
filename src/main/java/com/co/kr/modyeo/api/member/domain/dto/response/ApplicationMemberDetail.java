package com.co.kr.modyeo.api.member.domain.dto.response;

import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApplicationMemberDetail {

    private Long memberId;

    private Long applicationId;

    private String nickname;

    private Sex sex;

    private LocalDateTime birthDay;

    private LocalDateTime applicationTime;

    public ApplicationMemberDetail(Long memberId, Long applicationId, String nickname, Sex sex, LocalDateTime birthDay, LocalDateTime applicationTime) {
        this.memberId = memberId;
        this.applicationId = applicationId;
        this.nickname = nickname;
        this.sex = sex;
        this.birthDay = birthDay;
        this.applicationTime = applicationTime;
    }

    public void birthDayHide() {
        this.birthDay = null;
    }

    public void sexHide() {
        this.sex = null;
    }
}
