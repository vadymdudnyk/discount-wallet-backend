package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.campaign.shared.CreateCampaignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignFacade {
    private final CampaignService campaignService;
    void createCampaign(CreateCampaignRequest createCampaignRequest) {
        campaignService.createCampaign(createCampaignRequest);
    }

    public List<Campaign> getAllCampaignsByBusinessId(Long businessId) {
        return campaignService.getAllCampaignsByBusinessId(businessId);
    }
}
