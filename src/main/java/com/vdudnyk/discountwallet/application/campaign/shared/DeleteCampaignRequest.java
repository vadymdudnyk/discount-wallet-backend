package com.vdudnyk.discountwallet.application.campaign.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCampaignRequest {
    private Long businessId;
    private Long campaignId;
}
