package com.vdudnyk.discountwallet.application.business.shared;

import lombok.Value;

@Value
public class CustomerDTO {
    private Long id;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
