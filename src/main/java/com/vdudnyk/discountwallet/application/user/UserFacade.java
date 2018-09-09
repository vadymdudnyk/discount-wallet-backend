package com.vdudnyk.discountwallet.application.user;

import com.vdudnyk.discountwallet.application.user.shared.LoginRequest;
import com.vdudnyk.discountwallet.application.user.shared.RegisterWithPhoneNumberRequest;
import com.vdudnyk.discountwallet.application.user.shared.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;

    public void initRegistrationByPhoneNumber(RegisterWithPhoneNumberRequest request) {
        userService.initRegistrationWithPhoneNumber(request);
    }
    public TokenResponse authenticateByPhoneNumberAndOTP(LoginRequest request) {
        return userService.authenticateUserByOneTimePassword(request);
    }
}
