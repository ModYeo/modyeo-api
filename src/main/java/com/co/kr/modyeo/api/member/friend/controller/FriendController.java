package com.co.kr.modyeo.api.member.friend.controller;

import com.co.kr.modyeo.api.member.friend.service.FriendService;
import com.co.kr.modyeo.common.result.JsonResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api("친구 API Controller")
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService friendService;

    @ApiOperation(value = "친구 요청 API")
    @PostMapping("/{receriver_id}")
    public ResponseEntity<?> requestFriend(@PathVariable(value = "receiver_id") Long receiverId) {
        friendService.sendFriendRequest(receiverId);
        return ResponseEntity.ok(JsonResultData
                .successResultBuilder()
                .data(null)
                .build());
    }

    @ApiOperation(value = "친구 요청 전체 확인 API")
    @GetMapping("")
    public ResponseEntity<?> getFriendRequests() {
        return null;
    }

    @ApiOperation(value = "친구 요청 수락 API")
    @PostMapping("/accept/{friend_id}")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable(value = "friend_id") Long friendId) {
        return null;
    }

    @ApiOperation(value = "친구 요청 거부 API")
    @PostMapping("/deny/{friend_id}")
    public ResponseEntity<?> denyFriendRequest(@PathVariable(value = "friend_id") Long friendId) {
        return null;
    }
}
