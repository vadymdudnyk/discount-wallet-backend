package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.campaign.CampaignFacade;
import com.vdudnyk.discountwallet.application.campaign.shared.CampaignDTO;
import com.vdudnyk.discountwallet.application.campaign.shared.CreateCampaignRequest;
import com.vdudnyk.discountwallet.application.campaign.shared.DeleteCampaignRequest;
import com.vdudnyk.discountwallet.application.campaign.shared.UpdateCampaignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignFacade campaignFacade;

    @GetMapping("/businesses/{businessId}/campaigns")
    ResponseEntity<List<CampaignDTO>> getCampaigns(@PathVariable Long businessId) {
        return ResponseEntity.ok(campaignFacade.getAllCampaignsDTOByBusinessId(businessId));
    }

    @PostMapping("/businesses/{businessId}/campaigns")
    ResponseEntity<StatusResponse> createCampaign(@RequestBody CreateCampaignRequest createCampaignRequest) {
        campaignFacade.createCampaign(createCampaignRequest);
        return ResponseEntity.ok(StatusResponse.success());
    }

    @PutMapping("/businesses/{businessId}/campaigns/{campaignId}")
    ResponseEntity<StatusResponse> updateCampaign(@RequestBody UpdateCampaignRequest updateCampaignRequest) {
        campaignFacade.updateCampaign(updateCampaignRequest);
        return ResponseEntity.ok(StatusResponse.success());
    }

    @DeleteMapping("/businesses/{businessId}/campaigns/{campaignId}")
    ResponseEntity<StatusResponse> deleteCampaign(@PathVariable Long businessId, @PathVariable Long campaignId) {
        campaignFacade.deleteCampaign(new DeleteCampaignRequest(businessId, campaignId));
        return ResponseEntity.ok(StatusResponse.success());
    }
}
