package com.co.kr.modyeo.api.bbs.controller;

import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team-board")
@RequiredArgsConstructor
public class TeamBoardApiController {

    private final TeamBoardService teamBoardService;
}
