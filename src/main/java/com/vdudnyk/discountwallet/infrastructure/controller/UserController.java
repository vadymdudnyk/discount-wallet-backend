package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.user.UserFacade;
import com.vdudnyk.discountwallet.application.user.shared.LoginRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterAsMerchantRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterAsUserRequest;
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

    @PostMapping("/merchants")
    ResponseEntity<String> registerAsMerchant(@RequestBody RegisterAsMerchantRequest registerAsMerchantRequest) {
        userFacade.registerAsMerchant(registerAsMerchantRequest);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping
    ResponseEntity<String> registerAsUser(@RequestBody RegisterAsUserRequest request) {
        userFacade.registerAsUser(request);
        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping("/authenticate")
    ResponseEntity<TokenResponse> authenticateByPhoneNumberAndOtp(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userFacade.authenticate(request));
    }
}
