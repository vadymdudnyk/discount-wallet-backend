package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.campaign.shared.CreateCampaignRequest;
import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
 class CampaignService {
    private final CampaignRepository campaignRepository;
    private final UserFacade userFacade;
    private final BusinessFacade businessFacade;

    void createCampaign(CreateCampaignRequest createCampaignRequest) {
        Business business = businessFacade.getBusiness(createCampaignRequest.getBusinessId());
        validateBusinessOwnership(business);

        Campaign campaign = new Campaign();
        campaign.setBusiness(business);
        campaign.setCampaignType(createCampaignRequest.getCampaignType());
        campaign.setCouponType(createCampaignRequest.getCouponType());
        campaign.setDescription(createCampaignRequest.getDescription());
        campaign.setExpirationTime(createCampaignRequest.getExpirationTime());
        campaign.setMaxUsages(createCampaignRequest.getMaxUsages());
        campaignRepository.save(campaign);
    }

    List<Campaign> getAllCampaignsByBusinessId(Long businessId) {
        return campaignRepository.findAllByBusiness(businessId);
    }

    private void validateBusinessOwnership(Business business) {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        if (!business.getAdministrator().contains(authenticatedUser)) {
            throw new ApiException("Permission denied");
        }
    }


}
