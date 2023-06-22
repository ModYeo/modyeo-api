package com.co.kr.modyeo.api.member.friend.controller;

import com.co.kr.modyeo.api.member.friend.service.FriendService;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("친구 API Controller")
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/")
    public ResponseEntity<?> approvedFriends() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(friendService.getApprovedFriends(memberId))
                .build());
    }

    @ApiOperation(value = "친구 요청 API")
    @PostMapping("/request/{receiver-id}")
    public ResponseEntity<?> requestFriend(@PathVariable(value = "receiver_id") Long receiverId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        friendService.sendFriendRequest(memberId, receiverId);
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "받은 친구 요청 확인 API")
    @GetMapping("/requests/receive")
    public ResponseEntity<?> getReceiveFriendRequests() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(friendService.getReceiveFriendRequests(memberId))
                .build());
    }

    @ApiOperation(value = "보낸 친구 요청 확인 API")
    @GetMapping("/requests/send")
    public ResponseEntity<?> getSendFriendRequests() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(friendService.getSendFriendRequests(memberId))
                .build());
    }

    @ApiOperation(value = "친구 요청 수락 API")
    @PostMapping("/request/approve/{request-id}")
    public ResponseEntity<?> approveFriendRequest(@PathVariable(value = "request-id") Long friendId) {
        friendService.approveFriendRequest(friendId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "친구 요청 거부 API")
    @PostMapping("/request/deny/{request-id}")
    public ResponseEntity<?> denyFriendRequest(@PathVariable(value = "request-id") Long friendId) {
        friendService.denyFriendRequest(friendId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
