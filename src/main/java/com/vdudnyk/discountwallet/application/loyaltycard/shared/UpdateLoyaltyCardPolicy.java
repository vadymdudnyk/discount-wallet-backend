package com.vdudnyk.discountwallet.application.loyaltycard.shared;

import lombok.Data;

@Data
public class UpdateLoyaltyCardPolicy {
    private Long id;
    private Long businessId;
    private Long maxStamps;
    private String maxStampsCollectedText;
}
