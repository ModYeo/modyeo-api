package com.co.kr.modyeo.api.schedule.domain.entity;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "SCHEDULER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scheduler extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduler_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "emdAreaId")
    private EmdArea emdArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "categoryId")
    private Category category;

    private LocalDateTime meetingDate;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Scheduler(Long id, String title, String content, EmdArea emdArea, Category category, LocalDateTime meetingDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.emdArea = emdArea;
        this.category = category;
        this.meetingDate = meetingDate;
    }

    @Builder(builderClassName = "createBuilder",builderMethodName = "createBuilder")
    public static Scheduler create(String title, String content, EmdArea emdArea, Category category, LocalDateTime meetingDate){
        return of()
                .title(title)
                .content(content)
                .emdArea(emdArea)
                .category(category)
                .meetingDate(meetingDate)
                .build();
    }
}
