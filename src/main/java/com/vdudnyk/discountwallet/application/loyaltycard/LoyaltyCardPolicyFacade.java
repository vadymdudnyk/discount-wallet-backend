package com.vdudnyk.discountwallet.application.loyaltycard;

import com.vdudnyk.discountwallet.application.loyaltycard.shared.CreateLoyaltyCardPolicyCommand;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.UpdateLoyaltyCardPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoyaltyCardPolicyFacade {
    private final LoyaltyCardPolicyService loyaltyCardPolicyService;

    public void createLoyaltyCardPolicy(CreateLoyaltyCardPolicyCommand createLoyaltyCardPolicyCommand) {
        loyaltyCardPolicyService.createLoyaltyCardPolicy(createLoyaltyCardPolicyCommand);
    }

    public void updateLoyaltyCardPolicy(UpdateLoyaltyCardPolicy updateLoyaltyCardPolicy) {
        loyaltyCardPolicyService.updateLoyaltyCardPolicy(updateLoyaltyCardPolicy);
    }

    public LoyaltyCardPolicy getLoyaltyCardPolicy(Long businessId) {
        return loyaltyCardPolicyService.getLoyaltyCardPolicy(businessId);
    }
}
