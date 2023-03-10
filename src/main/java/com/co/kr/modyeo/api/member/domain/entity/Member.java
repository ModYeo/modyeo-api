package com.co.kr.modyeo.api.member.domain.entity;

import com.co.kr.modyeo.api.member.domain.entity.link.MemberActiveArea;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCategory;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCollectionInfo;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    private String nickname;

    private String description;

    @Column(name = "profile_path")
    private String profilePath;

    @Column(name = "birth_day")
    private LocalDate birthDay;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "last_access_token")
    private String lastAccessToken;

    @OneToMany(mappedBy = "member")
    private List<Crew> teamList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberCategory> interestCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberCollectionInfo> memberCollectionInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberActiveArea> memberActiveAreaList = new ArrayList<>();

    @Builder(buildMethodName = "createMemberBuilder", builderClassName = "createMemberBuilder")
    public Member(Long id, String email, String password, Authority authority, String nickname, Sex sex, LocalDate birthDay) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.nickname = nickname;
        this.sex = sex;
        this.birthDay = birthDay;
    }

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Member(Long id,
                  String email,
                  String password,
                  String username,
                  String nickname,
                  String profilePath,
                  LocalDate birthDay,
                  Sex sex,
                  Authority authority,
                  String description,
                  String lastAccessToken,
                  List<Crew> teamList,
                  List<MemberCategory> interestCategoryList,
                  List<MemberCollectionInfo> memberCollectionInfoList,
                  List<MemberActiveArea> memberActiveAreaList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.profilePath = profilePath;
        this.birthDay = birthDay;
        this.sex = sex;
        this.authority = authority;
        this.description = description;
        this.lastAccessToken = lastAccessToken;
        this.teamList = teamList;
        this.interestCategoryList = interestCategoryList;
        this.memberCollectionInfoList = memberCollectionInfoList;
        this.memberActiveAreaList = memberActiveAreaList;
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changeProfilePath(String profilePath) {this.profilePath = profilePath;}

    public void changeLastAccessToken(String accessToken) {
        this.lastAccessToken = accessToken;
    }

    public void changeDescription(String description) {this.description = description; }
}
