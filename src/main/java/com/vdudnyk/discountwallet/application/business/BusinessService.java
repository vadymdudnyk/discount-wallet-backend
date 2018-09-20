package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.business.shared.AddAdministratorToBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.RemoveAdministratorCommand;
import com.vdudnyk.discountwallet.application.business.shared.SetUpBusinessCommand;
import com.vdudnyk.discountwallet.application.business.shared.UpdateBusinessCommand;
import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserFacade userFacade;

    List<Business> getUserBusinesses() {
        return businessRepository.findAllByAdministrator(userFacade.getAuthenticatedUser());
    }

    Business getUserBusiness(Long businessId) {
        return businessRepository.findById(businessId)
                                 .filter(business -> business.getAdministrator().contains(userFacade.getAuthenticatedUser()))
                                 .orElseThrow(() -> new ApiException("Business not found"));

    }

    void setUpBusiness(SetUpBusinessCommand setUpBusinessRequest) {
        Business business = new Business();
        business.setAdministrator(Collections.singleton(userFacade.getAuthenticatedUser()));
        business.setBusinessName(setUpBusinessRequest.getBusinessName());
        business.setAddress(setUpBusinessRequest.getAddress());
        business.setCity(setUpBusinessRequest.getCity());
        business.setZipCode(setUpBusinessRequest.getZipCode());
        businessRepository.save(business);
    }

    void updateBusiness(UpdateBusinessCommand updateBusinessRequest) {
        Business business = getBusinessById(updateBusinessRequest.getId());

        if (!business.getAdministrator().contains(userFacade.getAuthenticatedUser())) {
            throw new ApiException("Cannot update business");
        }

        business.setZipCode(updateBusinessRequest.getZipCode());
        business.setCity(updateBusinessRequest.getCity());
        business.setBusinessName(updateBusinessRequest.getBusinessName());
        business.setAddress(updateBusinessRequest.getAddress());
        businessRepository.save(business);
    }

    void addAdministratorToBusiness(AddAdministratorToBusinessCommand addAdministratorToBusinessRequest) {
        Business business = getBusinessById(addAdministratorToBusinessRequest.getBusinessId());

        if (!business.getAdministrator().contains(userFacade.getAuthenticatedUser())) {
            throw new ApiException("Cannot update business, permission denied");
        }

        User newAdministrator = userFacade.getUserByUsername(addAdministratorToBusinessRequest.getUsernameOfAdministrator());
        business.getAdministrator().add(newAdministrator);
        businessRepository.save(business);
    }

    void removeAdministratorFromBusiness(RemoveAdministratorCommand removeAdministratorCommand) {
        Business business = getBusinessById(removeAdministratorCommand.getBusinessId());
        User authenticatedUser = userFacade.getAuthenticatedUser();
        if (business.getAdministrator().contains(authenticatedUser)) {
            throw new ApiException("Cannot update business, permission denied");
        }

        if (authenticatedUser.getEmail().equals(removeAdministratorCommand.getUsernameOfAdministrator()) ||
            authenticatedUser.getPhoneNumber().equals(removeAdministratorCommand.getUsernameOfAdministrator())) {
            throw new ApiException("Cannot remove yourself from business");
        }

        business.getAdministrator()
                .remove(userFacade.getUserByUsername(removeAdministratorCommand.getUsernameOfAdministrator()));
        businessRepository.save(business);

    }

    Business getBusiness(Long businessId) {
        return getBusinessById(businessId);
    }

    void addCustomer(User user, Long businessId) {
        Business business = getBusinessById(businessId);
        business.getCustomer().add(user);
        businessRepository.save(business);
    }

    private Business getBusinessById(Long businessId) {
        return businessRepository.findById(businessId).orElseThrow(() -> new ApiException("Business not found"));
    }
}
