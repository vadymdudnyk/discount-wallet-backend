package com.vdudnyk.discountwallet.application.loyaltycard;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class LoyaltyCard {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdDate;
    private Long businessId;
    private Long userId;
    private Long stamps;
    private Long points;
    private Long totalStampsCollected;
    private Long totalPointsCollects;
}
