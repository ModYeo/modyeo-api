package com.co.kr.modyeo.api.member.domain.entity;

import com.co.kr.modyeo.api.member.domain.entity.embed.Address;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.api.member.domain.enumerate.Sex;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCategory;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private List<Crew> teamList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberCategory> interestCategoryList = new ArrayList<>();

    @Builder(buildMethodName = "createMemberBuilder",builderClassName = "createMemberBuilder")
    public Member(Long id, String email, String password, Authority authority,String nickname, Sex sex) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.nickname = nickname;
        this.sex = sex;
    }

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Member(Long id,
                  String email,
                  String password,
                  String username,
                  String nickname,
                  Sex sex,
                  Authority authority,
                  List<Crew> teamList,
                  List<MemberCategory> interestCategoryList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.sex = sex;
        this.authority = authority;
        this.teamList = teamList;
        this.interestCategoryList = interestCategoryList;
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }
}
