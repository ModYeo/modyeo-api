package com.co.kr.modyeo.api.inquiry.repository;

import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.repository.custom.InquiryCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryCustomRepository {
}
