package com.vdudnyk.discountwallet.application.loyaltycard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyCardPolicyRepository extends JpaRepository<LoyaltyCardPolicy, Long> {
    Optional<LoyaltyCardPolicy> findByBusinessId(Long businessId);
}
