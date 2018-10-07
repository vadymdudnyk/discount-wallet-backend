package com.vdudnyk.discountwallet.application.loyaltycard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoyaltyCardService {
    private LoyaltyCardRepository loyaltyCardRepository;
    private LoyaltyCardPolicyService loyaltyCardPolicyService;

    void giveLoyaltyCardForUser(Long businessId, Long userId) {
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        loyaltyCard.setBusinessId(businessId);
        loyaltyCard.setCreatedDate(LocalDateTime.now());
        loyaltyCard.setPoints(0L);
        loyaltyCard.setStamps(0L);
        loyaltyCard.setTotalPointsCollects(0L);
        loyaltyCard.setTotalStampsCollected(0L);
        loyaltyCard.setUserId(userId);
        loyaltyCardRepository.save(loyaltyCard);
    }
}
