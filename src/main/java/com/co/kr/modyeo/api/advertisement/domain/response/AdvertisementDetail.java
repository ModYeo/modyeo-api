package com.co.kr.modyeo.api.advertisement.domain.response;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AdvertisementDetail {
    private Long advertisementId;

    private String advertisementName;

    private String urlLink;

    private String imagePath;

    private AdvertisementType type;

    private Yn useYn;

    private LocalDateTime createdDate;

    private java.time.LocalDateTime lastModifiedDate;

    private Long createdBy;

    private Long updatedBy;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public AdvertisementDetail(Long advertisementId, String advertisementName, String urlLink, String imagePath, AdvertisementType type, Yn useYn, LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long createdBy, Long updatedBy) {
        this.advertisementId = advertisementId;
        this.advertisementName = advertisementName;
        this.urlLink = urlLink;
        this.imagePath = imagePath;
        this.type = type;
        this.useYn = useYn;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public static AdvertisementDetail toDto(Advertisement advertisement){
        return of()
                .advertisementId(advertisement.getId())
                .advertisementName(advertisement.getName())
                .urlLink(advertisement.getUrlLink())
                .imagePath(advertisement.getImagePath())
                .type(advertisement.getType())
                .useYn(advertisement.getUseYn())
                .createdDate(advertisement.getCreatedDate())
                .createdBy(advertisement.getCreatedBy())
                .updatedBy(advertisement.getUpdatedBy())
                .lastModifiedDate(advertisement.getLastModifiedDate())
                .build();
    }
}
