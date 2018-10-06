package com.vdudnyk.discountwallet.application.loyaltycard.shared;

import lombok.Value;

@Value
public class CreateLoyaltyCardPolicyCommand {
    private Long businessId;
    private Long maxStamps;
    private String whatToDoIfMaxStampsCollected;
}
