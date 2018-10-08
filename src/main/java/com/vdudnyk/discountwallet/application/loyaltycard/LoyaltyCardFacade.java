package com.vdudnyk.discountwallet.application.loyaltycard;

import com.vdudnyk.discountwallet.application.loyaltycard.shared.CustomerLoyaltyCardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoyaltyCardFacade {
    private final LoyaltyCardService loyaltyCardService;

    public void giveLoyaltyCardForUser(Long businessId, Long userId) {
        loyaltyCardService.giveLoyaltyCardForUser(businessId, userId);
    }

    public List<CustomerLoyaltyCardDTO> getUsersLoyaltyCards(Long userId) {
        return loyaltyCardService.getUsersLoyaltyCards(userId);
    }

    public void activateStamp(Long businessId, Long loyaltyCardId, String activationCode) {
        loyaltyCardService.activateStamp(businessId, loyaltyCardId, activationCode);
    }
}
