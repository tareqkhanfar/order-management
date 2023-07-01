package com.khanfar.project2.Security.auth;

import com.khanfar.project2.Entity.Role;
import com.khanfar.project2.Security.config.JwtService;
import com.khanfar.project2.Security.user.User;
import com.khanfar.project2.Security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository ;

    private final PasswordEncoder passwordEncoder ;

    private final JwtService jwtService ;

    private final AuthenticationManager authenticationManager ;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return new AuthenticationResponse().builder().
                Token(token)
                .build();
    }

    public AuthenticationResponse authenticateRequest(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail() ,request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        return new AuthenticationResponse().builder().
                Token(token)
                .build();
    }


}
