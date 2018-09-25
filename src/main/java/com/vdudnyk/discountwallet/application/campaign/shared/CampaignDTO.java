package com.vdudnyk.discountwallet.application.campaign.shared;

import com.vdudnyk.discountwallet.application.campaign.CampaignType;
import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.Value;

@Value
public class CampaignDTO {
    private Long id;
    private Long businessId;
    private CampaignType campaignType;
    private CouponType couponType;
    private Long maxUsages;
    private String description;
    private Long expirationTime;
}
