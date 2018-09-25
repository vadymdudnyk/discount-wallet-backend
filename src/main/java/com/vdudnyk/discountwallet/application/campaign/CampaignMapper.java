package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.campaign.shared.CampaignDTO;
import org.springframework.stereotype.Component;

@Component
class CampaignMapper {
    CampaignDTO map(Campaign campaign) {
        return new CampaignDTO(
                campaign.getId(),
                campaign.getBusiness().getId(),
                campaign.getCampaignType(),
                campaign.getCouponType(),
                campaign.getMaxUsages(),
                campaign.getDescription(),
                campaign.getExpirationTime());
    }
}
