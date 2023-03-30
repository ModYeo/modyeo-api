package com.co.kr.modyeo.api.bbs.service.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamArticleRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRecommendRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.request.TeamReplyRequest;
import com.co.kr.modyeo.api.bbs.domain.dto.response.*;
import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamArticleRecommend;
import com.co.kr.modyeo.api.bbs.domain.entity.link.TeamReplyRecommend;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamArticleRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRecommendRepository;
import com.co.kr.modyeo.api.bbs.repository.TeamReplyRepository;
import com.co.kr.modyeo.api.bbs.service.TeamBoardService;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.member.repository.MemberRepository;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.exception.code.BoardErrorCode;
import com.co.kr.modyeo.common.exception.code.MemberErrorCode;
import com.co.kr.modyeo.common.exception.code.TeamErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamBoardServiceImpl implements TeamBoardService {

    private final TeamArticleRepository teamArticleRepository;

    private final TeamReplyRepository teamReplyRepository;

    private final TeamRepository teamRepository;

    private final MemberRepository memberRepository;

    private final TeamArticleRecommendRepository teamArticleRecommendRepository;

    private final TeamReplyRecommendRepository teamReplyRecommendRepository;

    @Override
    @Transactional
    public Long createTeamArticle(TeamArticleRequest teamArticleRequest) {
        Team team = teamRepository.findById(teamArticleRequest.getTeamId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode(TeamErrorCode.NOT_FOUND_TEAM.getCode())
                        .errorMessage(TeamErrorCode.NOT_FOUND_TEAM.getMessage())
                        .build());

        TeamArticle article = TeamArticleRequest.createArticle(teamArticleRequest, team);
        return teamArticleRepository.save(article).getId();
    }

    @Override
    @Transactional
    public TeamArticleDetail getTeamArticle(Long teamArticleId) {
        //TODO:: 성능 개선 필요
        TeamArticle teamArticle = teamArticleRepository.findTeamArticle(teamArticleId)
                .orElseThrow(() -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        teamArticle.plusHitCount();

        Member member = memberRepository.findById(teamArticle.getCreatedBy()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());


        TeamArticleDetail teamArticleDetail = TeamArticleDetail.toDto(teamArticle);
        teamArticleDetail.setMember(TeamArticleDetail.Member.toDto(member));

        List<TeamReplyResponse> replyResponses = teamReplyRepository.findByTeamArticleId(teamArticleId);
        teamArticleDetail.setReplyResponses(replyResponses);

        return teamArticleDetail;
    }

    @Override
    public Slice<TeamArticleResponse> getTeamArticles(TeamArticleSearch teamArticleSearch) {
        PageRequest pageRequest = PageRequest.of(teamArticleSearch.getOffset(), teamArticleSearch.getLimit(), teamArticleSearch.getDirection(), teamArticleSearch.getOrderBy());
        return teamArticleRepository.searchTeamArticle(teamArticleSearch, pageRequest);
    }

    @Override
    @Transactional
    public Long updateTeamArticle(TeamArticleRequest teamArticleRequest) {
        TeamArticle teamArticle = teamArticleRepository.findById(teamArticleRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .build());

        teamArticle.updateTeamArticleBuilder()
                .content(teamArticleRequest.getContent())
                .filePath(teamArticleRequest.getFilePath())
                .isHidden(teamArticleRequest.getIsHidden())
                .title(teamArticleRequest.getTitle())
                .build();

        return teamArticle.getId();
    }

    @Override
    @Transactional
    public void deleteTeamArticle(Long teamArticleId) {
        TeamArticle teamArticle = teamArticleRepository.findById(teamArticleId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .build());

        teamArticleRepository.delete(teamArticle);
    }

    @Override
    @Transactional
    public Long createTeamReply(TeamReplyRequest teamReplyRequest) {
        TeamArticle teamArticle = teamArticleRepository.findById(teamReplyRequest.getArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .build());

        TeamReply teamReply = teamReplyRequest.getReplyDepth() == 1 ?
                TeamReplyRequest.toTeamReply(teamArticle, teamReplyRequest.getContent()) : TeamReplyRequest.toTeamNestedReply(teamArticle, teamReplyRequest.getContent(), teamReplyRequest.getReplyGroup(), teamReplyRequest.getReplyDepth());

        teamArticle.plusReplyCount();
        return teamReplyRepository.save(teamReply).getId();
    }

    @Override
    @Transactional
    public Long updateTeamReply(TeamReplyRequest teamReplyRequest) {
        TeamReply teamReply = teamReplyRepository.findById(teamReplyRequest.getReplyId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .build());

        teamReply.changeTeamReply(teamReplyRequest.getContent());

        return teamReply.getId();
    }

    @Override
    @Transactional
    public void deleteTeamReply(Long teamReplyId) {
        TeamReply teamReply = teamReplyRepository.findById(teamReplyId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .build());

        teamReply.getTeamArticle().minusReplyCount();
        teamReplyRepository.delete(teamReply);
    }

    @Override
    public TeamReplyDetail getTeamReply(Long teamReplyId) {
        TeamReply teamReply = teamReplyRepository.findById(teamReplyId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage(BoardErrorCode.NOT_FOUND_REPLY.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_REPLY.getCode())
                        .build());

        List<TeamReply> nestedTeamReplyList = teamReplyRepository.findByReplyGroup(teamReply.getReplyGroup());
        return TeamReplyDetail.toDto(teamReply, nestedTeamReplyList);
    }

    @Override
    @Transactional
    public void updateTeamArticleRecommend(TeamArticleRecommendRequest articleRecommendRequest) {
        Member member = memberRepository.findById(articleRecommendRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        TeamArticle teamArticle = teamArticleRepository.findById(articleRecommendRequest.getTeamArticleId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        TeamArticleRecommend findTeamArticleRecommend = teamArticleRecommendRepository.findByMemberAndTeamArticle(member, teamArticle);

        if (findTeamArticleRecommend == null) {
            TeamArticleRecommend teamArticleRecommend = TeamArticleRecommend.createRecommendBuilder()
                    .member(member)
                    .teamArticle(teamArticle)
                    .build();

            teamArticleRecommendRepository.save(teamArticleRecommend);
            teamArticle.plusRecommendCount();
        } else {
            findTeamArticleRecommend.changeRecommendYn(articleRecommendRequest.getRecommendYn());
            if (Yn.Y.equals(articleRecommendRequest.getRecommendYn())){
                teamArticle.plusRecommendCount();
            } else {
                teamArticle.minusRecommendCount();
            }
        }
    }

    @Override
    @Transactional
    public void updateTeamReplyRecommend(TeamReplyRecommendRequest replyRecommendRequest) {
        Member member = memberRepository.findById(replyRecommendRequest.getMemberId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(MemberErrorCode.NOT_FOUND_MEMBER.getMessage())
                        .errorCode(MemberErrorCode.NOT_FOUND_MEMBER.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        TeamReply teamReply = teamReplyRepository.findById(replyRecommendRequest.getTeamReplyId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BoardErrorCode.NOT_FOUND_ARTICLE.getMessage())
                        .errorCode(BoardErrorCode.NOT_FOUND_ARTICLE.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        TeamReplyRecommend findTeamReplyRecommend = teamReplyRecommendRepository.findByMemberAndTeamReply(member, teamReply);

        if (findTeamReplyRecommend == null) {
            TeamReplyRecommend teamReplyRecommend = TeamReplyRecommend.createRecommendBuilder()
                    .member(member)
                    .teamReply(teamReply)
                    .build();

            teamReplyRecommendRepository.save(teamReplyRecommend);
        } else {
            findTeamReplyRecommend.changeRecommendYn(replyRecommendRequest.getRecommendYn());
        }
    }

    @Override
    public List<TeamReplyResponse> getReplyMy(Long memberId) {
        return teamArticleRepository.findReplyByMemberId(memberId)
                .stream().map(TeamReplyResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamArticleResponse> getArticleLike(Long memberId) {
        return teamArticleRepository.findArticleByEmailAndRecommendY(memberId)
                .stream().map(TeamArticleResponse::toDto)
                .collect(Collectors.toList());
    }
}
