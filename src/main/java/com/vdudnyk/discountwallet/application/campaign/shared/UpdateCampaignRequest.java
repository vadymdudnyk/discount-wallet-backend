package com.vdudnyk.discountwallet.application.campaign.shared;

import com.vdudnyk.discountwallet.application.campaign.CampaignType;
import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Wither;

@Data
@Wither
@AllArgsConstructor
public class UpdateCampaignRequest {
    private Long businessId;
    private Long campaignId;
    private CampaignType campaignType;
    private CouponType couponType;
    private Long maxUsages;
    private String description;
    private Long expirationTime;
}
