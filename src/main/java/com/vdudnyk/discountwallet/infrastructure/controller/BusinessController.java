package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.business.shared.*;
import com.vdudnyk.discountwallet.application.loyaltycard.LoyaltyCardPolicy;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.UpdateLoyaltyCardPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessFacade businessFacade;

    @GetMapping
    List<Business> getUserBusinesses() {
        log.info("GetUserBusinesses request");
        return businessFacade.getUserBusinesses();
    }

    @PostMapping
    ResponseEntity<Void> setUpBusiness(@RequestBody SetUpBusinessCommand setUpBusinessCommand) {
        log.info("SetUpBusiness request");
        businessFacade.setUpBusiness(setUpBusinessCommand);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    ResponseEntity<Void> updateBusiness(@RequestBody UpdateBusinessCommand updateBusinessCommand) {
        businessFacade.updateBusiness(updateBusinessCommand);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/administrators")
    ResponseEntity<Void> addAdministratorToBusiness(@RequestBody AddAdministratorToBusinessCommand addAdministratorToBusinessCommand) {
        businessFacade.addAdministratorToBusiness(addAdministratorToBusinessCommand);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/administrators")
    ResponseEntity<Void> removeAdministratorFromBusiness(@RequestBody RemoveAdministratorCommand removeAdministratorCommand) {
        businessFacade.removeAdministratorFromBusiness(removeAdministratorCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{businessId}/customers")
    ResponseEntity<List<CustomerDTO>> getBusinessCustomers(@PathVariable Long businessId) {
        return ResponseEntity.ok(businessFacade.getBusinessCustomers(businessId));
    }

    @GetMapping("{businessId}/events")
    ResponseEntity<List<BusinessEventDTO>> getBusinessEvents(@PathVariable Long businessId) {
        return ResponseEntity.ok(businessFacade.getBusinessEvents(businessId));
    }

    @GetMapping("{businessId}/loyaltyCardPolicy")
    ResponseEntity<LoyaltyCardPolicy> getLoyaltyCardPolicy(@PathVariable Long businessId) {
        return ResponseEntity.ok(businessFacade.getLoyaltyCardPolicy(businessId));
    }

    @PutMapping("{businessId}/loyaltyCardPolicy")
    ResponseEntity<Void> updateLoyaltyCardPolicy(@PathVariable Long businessId,
                                                 @RequestBody UpdateLoyaltyCardPolicy updateLoyaltyCardPolicy) {
        businessFacade.updateLoyaltyCardPolicy(updateLoyaltyCardPolicy);
        return ResponseEntity.ok().build();
    }
}
