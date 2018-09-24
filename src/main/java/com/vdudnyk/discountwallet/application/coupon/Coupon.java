package com.vdudnyk.discountwallet.application.coupon;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.campaign.Campaign;
import com.vdudnyk.discountwallet.application.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Coupon {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @ManyToOne
    private Business business;

    @ManyToOne
    private User user;

    private Long campaignId;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    private Long usages;
    private Long maxUsages;
    private Boolean active;
    private String description;

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

}
