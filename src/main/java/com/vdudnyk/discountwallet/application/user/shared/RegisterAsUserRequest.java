package com.vdudnyk.discountwallet.application.user.shared;

import lombok.Data;

@Data
public class RegisterAsUserRequest {
    private String phoneNumber;
    private String email;
    private String password;
}
