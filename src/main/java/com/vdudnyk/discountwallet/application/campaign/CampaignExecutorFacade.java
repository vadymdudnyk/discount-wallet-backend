package com.vdudnyk.discountwallet.application.campaign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignExecutorFacade {
    private CampaignExecutorService campaignExecutorService;

    public void executeWelcomeCampaign(Long businessId, Long userId){
        campaignExecutorService.executeWelcomeCampaigns(businessId, userId);
    }
}
