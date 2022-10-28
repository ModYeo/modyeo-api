package com.co.kr.modyeo.api.member.friend.controller;

import com.co.kr.modyeo.api.member.friend.service.FriendService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("친구 API Controller")
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/")
    public ResponseEntity<?> friends() {
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(friendService.getFriends())
                .build());
    }

    @ApiOperation(value = "친구 요청 API")
    @PostMapping("/request/{receriver_id}")
    public ResponseEntity<?> requestFriend(@PathVariable(value = "receiver_id") Long receiverId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        friendService.sendFriendRequest(username, receiverId);
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "친구 요청 전체 확인 API")
    @GetMapping("/request")
    public ResponseEntity<?> getFriendRequests() {
        return null;
    }

    @ApiOperation(value = "친구 요청 수락 API")
    @PostMapping("/request/accept/{friend_id}")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable(value = "friend_id") Long friendId) {
        return null;
    }

    @ApiOperation(value = "친구 요청 거부 API")
    @PostMapping("/request/deny/{friend_id}")
    public ResponseEntity<?> denyFriendRequest(@PathVariable(value = "friend_id") Long friendId) {
        return null;
    }
}
