package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.campaign.shared.CampaignDTO;
import com.vdudnyk.discountwallet.application.campaign.shared.CreateCampaignRequest;
import com.vdudnyk.discountwallet.application.campaign.shared.DeleteCampaignRequest;
import com.vdudnyk.discountwallet.application.campaign.shared.UpdateCampaignRequest;
import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
 class CampaignService {
    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;
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

    void updateCampaign(UpdateCampaignRequest updateCampaignRequest) {
        Business business = businessFacade.getBusiness(updateCampaignRequest.getBusinessId());
        validateBusinessOwnership(business);

        campaignRepository.findById(updateCampaignRequest.getCampaignId())
                          .ifPresent(campaign -> {
                              campaign.setMaxUsages(updateCampaignRequest.getMaxUsages());
                              campaign.setExpirationTime(updateCampaignRequest.getExpirationTime());
                              campaign.setDescription(updateCampaignRequest.getDescription());
                              campaign.setCouponType(updateCampaignRequest.getCouponType());
                              campaign.setCampaignType(updateCampaignRequest.getCampaignType());
                              campaignRepository.save(campaign);
                          });
    }

    void deleteCampaign(DeleteCampaignRequest deleteCampaignRequest) {
        Business business = businessFacade.getBusiness(deleteCampaignRequest.getBusinessId());
        validateBusinessOwnership(business);

        campaignRepository.deleteById(deleteCampaignRequest.getCampaignId());
    }

    List<CampaignDTO> getAllCampaignsDTOByBusinessId(Long businessId) {
        return getAllCampaignsByBusinessId(businessId)
                .stream()
                .map(campaignMapper::map)
                .collect(Collectors.toList());
    }

    List<Campaign> getAllCampaignsByBusinessId(Long businessId) {
        return campaignRepository.findAllByBusinessId(businessId);
    }

    private void validateBusinessOwnership(Business business) {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        if (!business.getAdministrator().contains(authenticatedUser)) {
            throw new ApiException("Permission denied");
        }
    }
}
