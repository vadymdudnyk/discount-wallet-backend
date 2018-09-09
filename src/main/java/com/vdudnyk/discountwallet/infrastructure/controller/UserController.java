package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.user.UserFacade;
import com.vdudnyk.discountwallet.application.user.shared.LoginRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterWithPhoneNumberRequest;
import com.vdudnyk.discountwallet.application.user.shared.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserFacade userFacade;

    @PostMapping("/register")
    ResponseEntity<String> initRegistrationByPhoneNumber(@RequestBody RegisterWithPhoneNumberRequest request) {
        userFacade.initRegistrationByPhoneNumber(request);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/authenticate")
    ResponseEntity<TokenResponse> authenticateByPhoneNumberAndOtp(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userFacade.authenticateByPhoneNumberAndOTP(request));
    }
}
