package com.co.kr.modyeo.api.schedule.domain.entity;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerStatus;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "emdAreaId")
    private EmdArea emdArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "categoryId")
    private Category category;

    private LocalDateTime meetingDate;

    private String meetingPlace;

    private int recruitmentCount;

    @Enumerated(value = EnumType.STRING)
    private SchedulerStatus schedulerStatus;

    @OneToMany(mappedBy = "scheduler")
    private List<MemberScheduler> memberSchedulerList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Scheduler(Long id, String title, String content, String imagePath, String meetingPlace, EmdArea emdArea, Category category, LocalDateTime meetingDate,SchedulerStatus schedulerStatus, List<MemberScheduler> memberSchedulerList, int recruitmentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.emdArea = emdArea;
        this.category = category;
        this.meetingDate = meetingDate;
        this.meetingPlace = meetingPlace;
        this.schedulerStatus = schedulerStatus;
        this.memberSchedulerList = memberSchedulerList;
        this.recruitmentCount = recruitmentCount;
    }

    @Builder(builderClassName = "createBuilder",builderMethodName = "createBuilder")
    public static Scheduler create(String title, String content,String imagePath, EmdArea emdArea, Category category, LocalDateTime meetingDate,String meetingPlace,int recruitmentCount){
        return of()
                .title(title)
                .content(content)
                .imagePath(imagePath)
                .emdArea(emdArea)
                .category(category)
                .meetingDate(meetingDate)
                .meetingPlace(meetingPlace)
                .schedulerStatus(SchedulerStatus.RECR)
                .recruitmentCount(recruitmentCount)
                .build();
    }

    public void updateStatus(SchedulerStatus status){
        this.schedulerStatus = status;
    }

    public void updateScheduler(Category category, String imagePath, String title, String content, Integer recruitmentCount) {
        this.category = category;
        if (imagePath != null) this.imagePath = imagePath;
        if (title != null) this.title = title;
        if (content != null) this.content = content;
        this.recruitmentCount = recruitmentCount;
    }
}
