package com.co.kr.modyeo.api.advertisement.repository;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
