package com.co.kr.modyeo.member.domain.entity.link;

import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.Category;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CREW_CATEGORY")
public class CrewCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
