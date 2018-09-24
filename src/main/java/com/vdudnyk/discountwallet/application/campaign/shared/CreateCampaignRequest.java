package com.vdudnyk.discountwallet.application.campaign.shared;

import com.vdudnyk.discountwallet.application.campaign.CampaignType;
import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.Data;

@Data
public class CreateCampaignRequest {
    private Long businessId;
    private CampaignType campaignType;
    private CouponType couponType;
    private Long maxUsages;
    private String description;
    private Long expirationTime;
}
