package com.vdudnyk.discountwallet.application.business.shared;

import lombok.Data;

@Data
public class SetUpBusinessCommand {
    private String businessName;
    private String city;
    private String address;
    private String zipCode;
}
