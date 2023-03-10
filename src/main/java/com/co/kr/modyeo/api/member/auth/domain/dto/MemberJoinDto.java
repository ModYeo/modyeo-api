package com.co.kr.modyeo.api.member.auth.domain.dto;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MemberJoinDto {

    @Setter
    private String email;

    @Setter
    private String password;

    private String username;

    private String nickname;

    private Sex sex;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate birthDay;

    @NotNull(message = "수집정보동의여부는 필수입니다.")
    private List<CollectionInfoDto> collectionInfoList;

    @NotNull(message = "카테고리 선택은 필수입니다.")
    private List<Long> categoryIdList;

    @NotNull(message = "활동지역 선택은 필수입니다.")
    private List<EmdAreaDto> emdAreaList;

    public MemberJoinDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberJoinDto(String email, String password, String username, String nickname, Sex sex, LocalDate birthDay, List<CollectionInfoDto> collectionInfoList, List<Long> categoryIdList, List<EmdAreaDto> emdAreaList) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.sex = sex;
        this.birthDay = birthDay;
        this.collectionInfoList = collectionInfoList;
        this.categoryIdList = categoryIdList;
        this.emdAreaList = emdAreaList;
    }

    public static Member toMember(MemberJoinDto memberJoinDto, PasswordEncoder passwordEncoder) {
        return Member.of()
                .email(memberJoinDto.email)
                .password(passwordEncoder.encode(memberJoinDto.password))
                .authority(Authority.ROLE_USER)
                .username(memberJoinDto.username)
                .nickname(memberJoinDto.nickname)
                .birthDay(memberJoinDto.getBirthDay())
                .sex(memberJoinDto.sex)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public EmdAreaDto getEmdAreaDto(Long emdAreaId){
        return this.emdAreaList.stream()
                .filter(emdAreaDto -> emdAreaDto.getId().equals(emdAreaId))
                .findFirst()
                .orElseThrow();
    }

    public CollectionInfoDto getCollectionInfo(Long collectionId) {
        return this.collectionInfoList.stream()
                .filter(collectionInfoDto -> collectionInfoDto.getId().equals(collectionId))
                .findFirst()
                .orElseThrow();
    }

    @Getter
    @Setter
    public static class CollectionInfoDto {

        private Long id;

        private Yn agreeYn;

        public static List<Long> getIdList(List<CollectionInfoDto> collectionInfoDtoList) {
            return collectionInfoDtoList.stream()
                    .map(MemberJoinDto.CollectionInfoDto::getId)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class EmdAreaDto {

        private Long id;

        private int limitMeters;

        public static List<Long> getIdList(List<EmdAreaDto> emdAreaDtoList) {
            return emdAreaDtoList.stream()
                    .map(MemberJoinDto.EmdAreaDto::getId)
                    .collect(Collectors.toList());
        }
    }
}
