package com.co.kr.modyeo.member.domain.entity.link;

import com.co.kr.modyeo.member.domain.entity.BaseEntity;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CREW_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CrewCategory extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public CrewCategory(Long id, Crew crew, Category category) {
        this.id = id;
        this.crew = crew;
        this.category = category;
    }
}
