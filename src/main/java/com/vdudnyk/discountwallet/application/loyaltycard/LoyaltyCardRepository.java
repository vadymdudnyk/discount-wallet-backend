package com.vdudnyk.discountwallet.application.loyaltycard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {
    List<LoyaltyCard> findAllByUserId(Long userId);
}
