package com.vdudnyk.discountwallet.application.user;

import com.vdudnyk.discountwallet.application.user.shared.LoginRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterAsMerchantRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterAsUserRequest;
import com.vdudnyk.discountwallet.application.user.shared.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public void registerAsUser(RegisterAsUserRequest request) {
        userService.registerAsUser(request);
    }

    public void registerAsMerchant(RegisterAsMerchantRequest registerAsMerchantRequest) {
        userService.registerAsMerchant(registerAsMerchantRequest);
    }

    public TokenResponse authenticate(LoginRequest request) {
        return userService.authenticateUserByOneTimePassword(request);
    }
}
