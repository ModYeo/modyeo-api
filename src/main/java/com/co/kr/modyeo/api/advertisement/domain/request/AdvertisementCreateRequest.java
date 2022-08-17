package com.co.kr.modyeo.api.advertisement.domain.request;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertisementCreateRequest {

    private String advertisementName;

    private String urlLink;

    private String imagePath;

    private AdvertisementType advertisementType;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public AdvertisementCreateRequest(String advertisementName, String urlLink, String imagePath, AdvertisementType advertisementType) {
        this.advertisementName = advertisementName;
        this.urlLink = urlLink;
        this.imagePath = imagePath;
        this.advertisementType = advertisementType;
    }

    public static Advertisement toEntity(AdvertisementCreateRequest advertisementCreateRequest){
        return Advertisement.createBuilder()
                .name(advertisementCreateRequest.getAdvertisementName())
                .type(advertisementCreateRequest.getAdvertisementType())
                .imagePath(advertisementCreateRequest.getImagePath())
                .urlLink(advertisementCreateRequest.getUrlLink())
                .build();
    }
}
