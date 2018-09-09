package com.vdudnyk.discountwallet.application.user.shared;

import lombok.Data;

@Data
public class LoginRequest {
    private final String phoneNumber;
    private final String oneTimePassword;
}
