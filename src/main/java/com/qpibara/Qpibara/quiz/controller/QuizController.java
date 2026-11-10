package com.qpibara.Qpibara.quiz.controller;

import com.qpibara.Qpibara.quiz.dto.QuizRequest;
import com.qpibara.Qpibara.quiz.dto.QuizResponse;
import com.qpibara.Qpibara.quiz.dto.QuizShortInfoResponse;
import com.qpibara.Qpibara.quiz.service.QuizService;
import com.qpibara.Qpibara.users.config.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
@Tag(name = "Quizzes", description = "Управление квизами")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(
            @RequestBody QuizRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(quizService.createQuiz(request, userDetails.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuiz(
            @PathVariable Long id,
            @RequestBody QuizRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(quizService.updateQuiz(id, request, userDetails.getId()));
    }

    @GetMapping

    public ResponseEntity<List<QuizShortInfoResponse>> getMyQuizzes(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(quizService.getMyQuizzes(userDetails.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        quizService.deleteQuiz(id, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}