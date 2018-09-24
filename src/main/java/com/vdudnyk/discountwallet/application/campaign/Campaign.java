package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Campaign {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Business business;

    @Enumerated(EnumType.STRING)
    private CampaignType campaignType;

    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    private Long maxUsages;
    private String description;
    private Long expirationTime;
}
