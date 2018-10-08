package com.vdudnyk.discountwallet.application.loyaltycard.shared;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CustomerLoyaltyCardDTO {
    private Long id;
    private LocalDateTime createdDate;
    private Long businessId;
    private Long userId;
    private Long stamps;
    private Long points;
    private Long totalStampsCollected;
    private Long totalPointsCollects;
    private String activationCode;
}
