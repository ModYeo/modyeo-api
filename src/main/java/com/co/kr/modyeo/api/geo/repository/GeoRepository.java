package com.co.kr.modyeo.api.geo.repository;

import com.co.kr.modyeo.api.geo.domain.Geo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoRepository extends JpaRepository<Geo,Long> {
}
