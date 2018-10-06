package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.business.shared.*;
import com.vdudnyk.discountwallet.application.loyaltycard.LoyaltyCardPolicy;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.UpdateLoyaltyCardPolicy;
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

    public List<CustomerDTO> getBusinessCustomers(Long businessId) {
        return businessService.getBusinessCustomers(businessId);
    }

    public List<BusinessEventDTO> getBusinessEvents(Long businessId) {
        return businessService.getBusinessEvents(businessId);
    }

    public LoyaltyCardPolicy getLoyaltyCardPolicy(Long businessId) {
        return businessService.getLoyaltyCardPolicy(businessId);
    }

    public void updateLoyaltyCardPolicy(UpdateLoyaltyCardPolicy updateLoyaltyCardPolicy) {
        businessService.updateLoyaltyCardPolicy(updateLoyaltyCardPolicy);
    }
}
