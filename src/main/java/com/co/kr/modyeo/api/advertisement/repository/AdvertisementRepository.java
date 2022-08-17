package com.co.kr.modyeo.api.advertisement.repository;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByUseYnAndType(Yn yn, AdvertisementType advertisementType);
}
