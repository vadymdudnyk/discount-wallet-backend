package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.business.Business;
import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.business.shared.AddAdministratorToBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.RemoveAdministratorCommand;
import com.vdudnyk.discountwallet.application.business.shared.SetUpBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.UpdateBusinessCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    void setUpBusiness(@RequestBody SetUpBusinessCommand setUpBusinessCommand) {
        log.info("SetUpBusiness request");
        businessFacade.setUpBusiness(setUpBusinessCommand);
    }

    @PutMapping
    void updateBusiness(@RequestBody UpdateBusinessCommand updateBusinessCommand) {
        businessFacade.updateBusiness(updateBusinessCommand);
    }

    @PostMapping("/administrators")
    void addAdministratorToBusiness(@RequestBody AddAdministratorToBusinessCommand addAdministratorToBusinessCommand) {
        businessFacade.addAdministratorToBusiness(addAdministratorToBusinessCommand);
    }

    @DeleteMapping("/administrators")
    void removeAdministratorFromBusiness(@RequestBody RemoveAdministratorCommand removeAdministratorCommand) {
        businessFacade.removeAdministratorFromBusiness(removeAdministratorCommand);
    }
}
