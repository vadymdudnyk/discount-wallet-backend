package com.vdudnyk.discountwallet.application.user.shared;

import lombok.Data;

@Data
public class RegisterAsMerchantRequest {
    private String email;
    private String password;
    private String phoneNumber;
}
