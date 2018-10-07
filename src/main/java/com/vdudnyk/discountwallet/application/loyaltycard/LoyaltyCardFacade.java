package com.vdudnyk.discountwallet.application.loyaltycard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoyaltyCardFacade {
    private final LoyaltyCardService loyaltyCardService;

    public void giveLoyaltyCardForUser(Long businessId, Long userId) {
        loyaltyCardService.giveLoyaltyCardForUser(businessId, userId);
    }
}
