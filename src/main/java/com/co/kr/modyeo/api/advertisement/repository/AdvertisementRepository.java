package com.co.kr.modyeo.api.advertisement.repository;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementSearch;
import com.co.kr.modyeo.api.advertisement.repository.custom.AdvertisementCustomRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, AdvertisementCustomRepository {
}
