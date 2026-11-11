package com.qpibara.Qpibara.event.controller;

import com.qpibara.Qpibara.event.dto.*;
import com.qpibara.Qpibara.event.service.SessionService;
import com.qpibara.Qpibara.users.config.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping("/start/{eventId}")
    public ResponseEntity<SessionResponse> startSession(
            @PathVariable Long eventId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(sessionService.startSession(eventId, userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getSession(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSessionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionResponse> updateSession(
            @PathVariable Long id,
            @RequestBody SessionUpdateRequest request) {
        return ResponseEntity.ok(sessionService.updateSession(id, request));
    }

    @PutMapping("/{sessionId}/teams/{teamId}")
    public ResponseEntity<SessionResponse> updateTeam(
            @PathVariable Long sessionId,
            @PathVariable Long teamId,
            @RequestBody TeamRequest request) {
        return ResponseEntity.ok(sessionService.updateTeam(sessionId, teamId, request));
    }

    @PutMapping("/{sessionId}/members/{memberId}")
    public ResponseEntity<SessionResponse> updateMember(
            @PathVariable Long sessionId,
            @PathVariable Long memberId,
            @RequestBody MemberRequest request) {
        return ResponseEntity.ok(sessionService.updateMember(sessionId, memberId, request));
    }

    @PostMapping("/{sessionId}/answers")
    public ResponseEntity<Void> saveAnswer(
            @PathVariable Long sessionId,
            @RequestBody SessionAnswerRequest request) {
        sessionService.saveAnswer(sessionId, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/timer")
    public ResponseEntity<SessionResponse> updateTimer(
            @PathVariable Long id,
            @RequestBody TimerRequest request) {
        return ResponseEntity.ok(sessionService.updateTimer(id, request));
    }
}