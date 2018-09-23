package com.vdudnyk.discountwallet.application.customer.dto;

import lombok.Value;

@Value
public class DiscoveredBusinessDTO {
    private Long id;
    private String businessName;
    private String city;
    private String address;
    private String zipCode;
    private Boolean isSubscribed;
}
