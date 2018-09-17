package com.vdudnyk.discountwallet.application.business.shared;

import lombok.Data;

@Data
public class UpdateBusinessCommand {
    private Long id;
    private String businessName;
    private String city;
    private String address;
    private String zipCode;
}
