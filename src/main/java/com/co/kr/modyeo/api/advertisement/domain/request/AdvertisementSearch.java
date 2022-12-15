package com.co.kr.modyeo.api.advertisement.domain.request;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class AdvertisementSearch extends SearchDto {
    private AdvertisementType advertisementType;

    public AdvertisementSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        super(limit, offset, orderBy, direction);
    }
}
