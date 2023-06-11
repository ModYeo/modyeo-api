package com.co.kr.modyeo.api.schedule.domain.entity;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerStatus;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerType;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

    @Column(name = "scheduler_title")
    private String title;

    @Lob
    @Column(name = "scheduler_content")
    private String content;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emd_area_id")
    private EmdArea emdArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "meeting_date")
    private LocalDateTime meetingDate;

    @Column(name = "meeting_place")
    private String meetingPlace;

    @Column(name = "recruitment_count")
    private int recruitmentCount;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scheduler_status")
    private SchedulerStatus schedulerStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scheduler_type")
    private SchedulerType schedulerType;

    @OneToMany(mappedBy = "scheduler")
    private List<MemberScheduler> memberSchedulerList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Scheduler(Long id,
                     String title,
                     String content,
                     String imagePath,
                     String meetingPlace,
                     EmdArea emdArea,
                     Category category,
                     LocalDateTime meetingDate,
                     SchedulerStatus schedulerStatus,
                     List<MemberScheduler> memberSchedulerList,
                     int recruitmentCount,
                     SchedulerType schedulerType) {
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
        this.schedulerType = schedulerType;
    }

    @Builder(builderClassName = "createBuilder",builderMethodName = "createBuilder")
    public static Scheduler create(String title,
                                   String content,
                                   String imagePath,
                                   EmdArea emdArea,
                                   Category category,
                                   LocalDateTime meetingDate,
                                   String meetingPlace,
                                   int recruitmentCount,
                                   SchedulerType schedulerType){
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
                .schedulerType(schedulerType)
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

    public MemberScheduler getHost(){
        return this.memberSchedulerList.stream()
                .filter(memberScheduler -> ApplicationType.MADE.equals(memberScheduler.getApplicationType()))
                .findFirst()
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorCode("NOT_FOUND_HOST")
                        .errorMessage("호스트를 찾을 수 없습니다.")
                        .build());
    }
}
