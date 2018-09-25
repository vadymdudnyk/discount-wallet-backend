package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.campaign.shared.CampaignDTO;
import com.vdudnyk.discountwallet.application.campaign.shared.CreateCampaignRequest;
import com.vdudnyk.discountwallet.application.campaign.shared.DeleteCampaignRequest;
import com.vdudnyk.discountwallet.application.campaign.shared.UpdateCampaignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignFacade {
    private final CampaignService campaignService;

    public void createCampaign(CreateCampaignRequest createCampaignRequest) {
        campaignService.createCampaign(createCampaignRequest);
    }

    public List<Campaign> getAllCampaignsByBusinessId(Long businessId) {
        return campaignService.getAllCampaignsByBusinessId(businessId);
    }

    public List<CampaignDTO> getAllCampaignsDTOByBusinessId(Long businessId) {
        return campaignService.getAllCampaignsDTOByBusinessId(businessId);
    }
    public void updateCampaign(UpdateCampaignRequest updateCampaignRequest) {
        campaignService.updateCampaign(updateCampaignRequest);
    }

    public void deleteCampaign(DeleteCampaignRequest deleteCampaignRequest) {
        campaignService.deleteCampaign(deleteCampaignRequest);
    }
}
