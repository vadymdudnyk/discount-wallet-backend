package com.vdudnyk.discountwallet.application.user;

import com.vdudnyk.discountwallet.application.user.shared.AuthenticateRequest;
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

    public TokenResponse registerAsMerchant(RegisterAsMerchantRequest registerAsMerchantRequest) {
        return userService.registerAsMerchant(registerAsMerchantRequest);
    }

    public TokenResponse authenticate(AuthenticateRequest request) {
        return userService.authenticate(request);
    }

    public User getAuthenticatedUser() {
        return userService.getAuthenticatedUser();
    }

    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }
}
