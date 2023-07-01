package com.khanfar.project2.Security.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        System.out.println(registerRequest.getEmail());
         return ResponseEntity.ok(authenticationService.register(registerRequest));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest(@RequestBody AuthenticationRequest Request) {
        return ResponseEntity.ok(authenticationService.authenticateRequest(Request));


    }


}
