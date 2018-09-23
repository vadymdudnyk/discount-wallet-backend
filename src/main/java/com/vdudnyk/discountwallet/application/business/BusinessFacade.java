package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.business.shared.AddAdministratorToBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.RemoveAdministratorCommand;
import com.vdudnyk.discountwallet.application.business.shared.SetUpBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.UpdateBusinessCommand;
import com.vdudnyk.discountwallet.application.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessFacade {
    private final BusinessService businessService;

    public List<Business> getUserBusinesses() {
        return businessService.getUserBusinesses();
    }

    public Business getUserBusiness(Long businessId) {
        return businessService.getUserBusiness(businessId);
    }

    public Business getBusiness(Long businessId) {
        return businessService.getBusiness(businessId);
    }

    public List<Business> getAllBusinesses() {
        return businessService.getAllBusinesses();
    }
    public void addCustomer(User user, Long businessId) {
        businessService.addCustomer(user, businessId);
    }

    public void setUpBusiness(SetUpBusinessCommand setUpBusinessCommand) {
        businessService.setUpBusiness(setUpBusinessCommand);
    }

    public void updateBusiness(UpdateBusinessCommand updateBusinessCommand) {
        businessService.updateBusiness(updateBusinessCommand);
    }

    public void addAdministratorToBusiness(AddAdministratorToBusinessCommand addAdministratorToBusinessCommand) {
        businessService.addAdministratorToBusiness(addAdministratorToBusinessCommand);
    }

    public void removeAdministratorFromBusiness(RemoveAdministratorCommand removeAdministratorCommand) {
        businessService.removeAdministratorFromBusiness(removeAdministratorCommand);
    }
}
