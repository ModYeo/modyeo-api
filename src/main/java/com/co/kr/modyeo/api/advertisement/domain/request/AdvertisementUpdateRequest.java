package com.co.kr.modyeo.api.advertisement.domain.request;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertisementUpdateRequest {

    private Long id;

    private String advertisementName;

    private String urlLink;

    private String imagePath;

    private AdvertisementType advertisementType;

}
