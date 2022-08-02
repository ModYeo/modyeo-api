package com.co.kr.modyeo.api.member.auth.domain.dto;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.domain.entity.embed.Address;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.common.enumerate.Yn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MemberJoinDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String username;

    private String nickname;

    private Sex sex;

    private List<CollectionInfoDto> collectionInfoDtoList;

    public MemberJoinDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MemberJoinDto(String email, String password, String username, Sex sex) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.sex = sex;
    }

    public static Member toMember(MemberJoinDto memberJoinDto, PasswordEncoder passwordEncoder) {
        return Member.of()
                .email(memberJoinDto.email)
                .password(passwordEncoder.encode(memberJoinDto.password))
                .authority(Authority.ROLE_USER)
                .username(memberJoinDto.username)
                .nickname(memberJoinDto.nickname)
                .sex(memberJoinDto.sex)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public CollectionInfoDto getCollectionInfo(Long collectionId){
        return this.collectionInfoDtoList.stream()
                .filter(collectionInfoDto -> collectionInfoDto.getId().equals(collectionId))
                .findFirst()
                .orElseThrow();
    }

    @Getter
    public static class CollectionInfoDto{

        private Long id;

        private Yn agreeYn;

        public static List<Long> getIdList(List<CollectionInfoDto> collectionInfoDtoList){
            return collectionInfoDtoList.stream()
                    .map(MemberJoinDto.CollectionInfoDto::getId)
                    .collect(Collectors.toList());
        }
    }
}
