package com.qpibara.Qpibara.users.controller;

import com.qpibara.Qpibara.users.dto.AuthRequest;
import com.qpibara.Qpibara.users.dto.AuthResponse;
import com.qpibara.Qpibara.users.dto.RegisterRequest;
import com.qpibara.Qpibara.users.repository.UserRepository;
import com.qpibara.Qpibara.users.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(userRepository.existsByUsername(username));
    }


    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(userRepository.existsByEmail(email));
    }
}