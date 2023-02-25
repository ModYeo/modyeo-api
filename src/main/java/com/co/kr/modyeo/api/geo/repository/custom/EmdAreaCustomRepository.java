package com.co.kr.modyeo.api.geo.repository.custom;

import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;

import java.util.List;

public interface EmdAreaCustomRepository {
    List<EmdAreaDetail> findByNameContains(String name);
}
