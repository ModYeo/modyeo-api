package com.co.kr.modyeo.api.advertisement.domain.request;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdvertisementSearch extends SearchDto {
    private AdvertisementType advertisementType;
}
