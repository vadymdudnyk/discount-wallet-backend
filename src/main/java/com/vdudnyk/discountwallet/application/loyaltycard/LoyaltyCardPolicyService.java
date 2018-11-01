package com.vdudnyk.discountwallet.application.loyaltycard;

import com.vdudnyk.discountwallet.application.loyaltycard.shared.CreateLoyaltyCardPolicyCommand;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.UpdateLoyaltyCardPolicy;
import com.vdudnyk.discountwallet.application.shared.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoyaltyCardPolicyService {
    private final LoyaltyCardPolicyRepository loyaltyCardPolicyRepository;

    void createLoyaltyCardPolicy(CreateLoyaltyCardPolicyCommand createLoyaltyCardPolicyCommand) {
        LoyaltyCardPolicy loyaltyCardPolicy = new LoyaltyCardPolicy();
        loyaltyCardPolicy.setBusinessId(createLoyaltyCardPolicyCommand.getBusinessId());
        loyaltyCardPolicy.setCreatedDate(LocalDateTime.now());
        loyaltyCardPolicyRepository.save(loyaltyCardPolicy);
    }

    void updateLoyaltyCardPolicy(UpdateLoyaltyCardPolicy updateLoyaltyCardPolicy) {
        loyaltyCardPolicyRepository.findById(updateLoyaltyCardPolicy.getId())
                                   .ifPresent(loyaltyCardPolicy -> {
                                       loyaltyCardPolicy.setMaxStamps(updateLoyaltyCardPolicy.getMaxStamps());
                                       loyaltyCardPolicy.setMaxStampsCollectedText(updateLoyaltyCardPolicy.getMaxStampsCollectedText());
                                       loyaltyCardPolicyRepository.save(loyaltyCardPolicy);
                                   });
    }

    LoyaltyCardPolicy getLoyaltyCardPolicy(Long businessId) {
        return loyaltyCardPolicyRepository.findByBusinessId(businessId)
                                          .orElseThrow(() -> new ApiException("Cant find loyalty card policy"));
    }
}
