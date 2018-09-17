package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.business.shared.AddAdministratorToBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.RemoveAdministratorCommand;
import com.vdudnyk.discountwallet.application.business.shared.SetUpBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.UpdateBusinessCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessFacade {
    private final BusinessService businessService;

    List<Business> getUserBusinesses() {
        return businessService.getBusinesses();
    }

    void setUpBusiness(SetUpBusinessCommand setUpBusinessCommand) {
        businessService.setUpBusiness(setUpBusinessCommand);
    }

    void updateBusiness(UpdateBusinessCommand updateBusinessCommand) {
        businessService.updateBusiness(updateBusinessCommand);
    }

    void addAdministratorToBusiness(AddAdministratorToBusinessCommand addAdministratorToBusinessCommand) {
        businessService.addAdministratorToBusiness(addAdministratorToBusinessCommand);
    }

    void removeAdministratorFromBusiness(RemoveAdministratorCommand removeAdministratorCommand) {
        businessService.removeAdministratorFromBusiness(removeAdministratorCommand);
    }
}
