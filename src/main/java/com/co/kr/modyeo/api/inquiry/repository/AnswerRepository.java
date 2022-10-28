package com.co.kr.modyeo.api.inquiry.repository;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.repository.custom.AnswerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerCustomRepository {
}
