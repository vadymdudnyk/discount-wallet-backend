package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.user.UserFacade;
import com.vdudnyk.discountwallet.application.user.shared.*;
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
    ResponseEntity<TokenResponse> registerAsMerchant(@RequestBody RegisterAsMerchantRequest registerAsMerchantRequest) {
        TokenResponse tokenResponse = userFacade.registerAsMerchant(registerAsMerchantRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping
    ResponseEntity<TokenResponse> registerAsUser(@RequestBody RegisterAsUserRequest request) {
        TokenResponse tokenResponse = userFacade.registerAsUser(request);
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/authentications")
    ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok(userFacade.authenticate(request));
    }
}
