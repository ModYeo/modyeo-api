package com.co.kr.modyeo.member.domain.entity;

import com.co.kr.modyeo.member.domain.entity.embed.Address;
import com.co.kr.modyeo.member.domain.entity.link.MemberCategory;
import com.co.kr.modyeo.member.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.member.domain.enumerate.Authority;
import com.co.kr.modyeo.member.domain.enumerate.Sex;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String username;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberTeam> memberTeamList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberCategory> interestCategoryList = new ArrayList<>();

    @Builder(buildMethodName = "createMemberBuilder",builderClassName = "createMemberBuilder")
    public Member(Long id, String email, String password, Authority authority, Sex sex) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.sex = sex;
    }

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Member(Long id,
                  String email,
                  String password,
                  String username,
                  Address address,
                  Sex sex,
                  Authority authority,
                  List<MemberTeam> memberTeamList,
                  List<MemberCategory> interestCategoryList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.sex = sex;
        this.authority = authority;
        this.memberTeamList = memberTeamList;
        this.interestCategoryList = interestCategoryList;
    }
}
