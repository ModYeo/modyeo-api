package com.co.kr.modyeo.member.domain.entity;

import com.co.kr.modyeo.member.domain.entity.embed.Address;
import com.co.kr.modyeo.member.domain.entity.link.MemberCategory;
import com.co.kr.modyeo.member.domain.entity.link.MemberCrew;
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
    private List<MemberCrew> memberCrewList = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberCategory> interestCategoryList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Member(String email, String password, Authority authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
}
