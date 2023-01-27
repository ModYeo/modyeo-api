package com.co.kr.modyeo.api.member.domain.dto.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class MemberSearch extends SearchDto {

    private String username;

    private String nickname;


    public MemberSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction, String username, String nikname) {
        super(limit, offset, orderBy, direction);
        this.username = username;
        this.nickname = nikname;
    }
}
