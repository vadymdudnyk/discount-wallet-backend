package com.vdudnyk.discountwallet.application.loyaltycard;

import com.vdudnyk.discountwallet.application.event.EventFactory;
import com.vdudnyk.discountwallet.application.event.EventSender;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.CustomerLoyaltyCardDTO;
import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.shared.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoyaltyCardService {
    private final LoyaltyCardRepository loyaltyCardRepository;
    private final LoyaltyCardPolicyService loyaltyCardPolicyService;
    private final EventSender eventSender;

    void giveLoyaltyCardForUser(Long businessId, Long userId) {
        LoyaltyCardPolicy loyaltyCardPolicy = loyaltyCardPolicyService.getLoyaltyCardPolicy(businessId);
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        loyaltyCard.setBusinessId(businessId);
        loyaltyCard.setCreatedDate(LocalDateTime.now());
        loyaltyCard.setPoints(0L);
        loyaltyCard.setStamps(0L);
        loyaltyCard.setMaxStamps(loyaltyCardPolicy.getMaxStamps());
        loyaltyCard.setTotalPointsCollects(0L);
        loyaltyCard.setTotalStampsCollected(0L);
        loyaltyCard.setUserId(userId);
        loyaltyCard.setActivationCode(CodeGenerator.generateSimpleCode());
        loyaltyCardRepository.save(loyaltyCard);
    }

    List<CustomerLoyaltyCardDTO> getUsersLoyaltyCards(Long userId) {
        return loyaltyCardRepository.findAllByUserId(userId)
                                    .stream()
                                    .map(loyaltyCard -> new CustomerLoyaltyCardDTO(
                                            loyaltyCard.getId(),
                                            loyaltyCard.getCreatedDate(),
                                            loyaltyCard.getBusinessId(),
                                            loyaltyCard.getUserId(),
                                            loyaltyCard.getStamps(),
                                            loyaltyCard.getPoints(),
                                            loyaltyCard.getTotalStampsCollected(),
                                            loyaltyCard.getTotalPointsCollects(),
                                            loyaltyCard.getActivationCode()
                                    ))
                                    .collect(Collectors.toList());
    }

    void activateStamp(Long businessId, Long loyaltyCardId, String activationCode) {
        Optional<LoyaltyCard> optionalLoyaltyCard = loyaltyCardRepository.findById(loyaltyCardId);
        if (!optionalLoyaltyCard.isPresent()) {
            throw new ApiException("Cant find loyalty card");
        }

        LoyaltyCard loyaltyCard = optionalLoyaltyCard.get();
        if (!loyaltyCard.getActivationCode().equals(activationCode)) {
            throw new ApiException("Activation code does not match loyalty card code");
        }

        loyaltyCard.setStamps(loyaltyCard.getStamps() + 1);
        loyaltyCard.setTotalStampsCollected(loyaltyCard.getTotalStampsCollected() + 1);
        loyaltyCardRepository.save(loyaltyCard);

        eventSender.send(EventFactory.createStampCollectedEvent(loyaltyCard.getUserId(),
                                                                loyaltyCard.getBusinessId(),
                                                                loyaltyCard.getId(),
                                                                loyaltyCard.getStamps()));

        if (loyaltyCard.getStamps().equals(loyaltyCard.getMaxStamps())) {
            eventSender.send(EventFactory.createMaxStampsCollectedEvent(loyaltyCard.getUserId(), businessId, loyaltyCardId, activationCode));
        }
    }
}
