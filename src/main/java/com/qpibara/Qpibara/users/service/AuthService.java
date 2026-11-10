package com.qpibara.Qpibara.users.service;

import com.qpibara.Qpibara.config.exepitions.BusinessCode;
import com.qpibara.Qpibara.config.exepitions.BusinessException;
import com.qpibara.Qpibara.users.config.jwt.CustomUserDetails;
import com.qpibara.Qpibara.users.config.jwt.JwtService;
import com.qpibara.Qpibara.users.dto.AuthRequest;
import com.qpibara.Qpibara.users.dto.AuthResponse;
import com.qpibara.Qpibara.users.dto.RegisterRequest;
import com.qpibara.Qpibara.users.model.User;
import com.qpibara.Qpibara.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new BusinessException(BusinessCode.USER_ALREADY_EXISTS);
        }

        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .name(request.name())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);

        var userDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);

        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        var user = userRepository.findByUsername(request.username()).orElseThrow();
        var userDetails = new CustomUserDetails(user);
        var jwtToken = jwtService.generateToken(userDetails);

        return new AuthResponse(jwtToken);
    }
}
