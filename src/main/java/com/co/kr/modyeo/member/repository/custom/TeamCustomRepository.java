package com.co.kr.modyeo.member.repository.custom;

import com.co.kr.modyeo.member.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.member.domain.entity.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TeamCustomRepository {
    Slice<Team> searchTeam(TeamSearch teamSearch, Pageable pageable);
}
