package com.vdudnyk.discountwallet.application.event.payload;

import lombok.Value;

@Value
public class UserDetails {
    private Long id;
    private String email;
    private String phoneNumber;
}
