package com.co.kr.modyeo.api.advertisement.domain.request;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
public class AdvertisementSearch {
    private AdvertisementType advertisementType;

    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;
}
