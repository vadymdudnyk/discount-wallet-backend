package com.vdudnyk.discountwallet.application.user.shared;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private final String username;
    private final String password;
}

