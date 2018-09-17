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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserFacade userFacade;

    List<Business> getBusinesses() {
        return businessRepository.findAllByAdministrators(userFacade.getAuthenticatedUser());
    }

    void setUpBusiness(SetUpBusinessCommand setUpBusinessRequest) {
        Business business = new Business();
        business.setAdministrators(Collections.singleton(userFacade.getAuthenticatedUser()));
        business.setBusinessName(setUpBusinessRequest.getBusinessName());
        business.setAddress(setUpBusinessRequest.getAddress());
        business.setCity(setUpBusinessRequest.getCity());
        business.setZipCode(setUpBusinessRequest.getZipCode());
        businessRepository.save(business);
    }

    void updateBusiness(UpdateBusinessCommand updateBusinessRequest) {
        Optional<Business> businessOptional = businessRepository.findById(updateBusinessRequest.getId());
        if (!businessOptional.isPresent()) {
            throw new ApiException("Cannot find business");
        }
        Business business = businessOptional.get();
        if (!business.getAdministrators().contains(userFacade.getAuthenticatedUser())) {
            throw new ApiException("Cannot update business");
        }

        business.setZipCode(updateBusinessRequest.getZipCode());
        business.setCity(updateBusinessRequest.getCity());
        business.setBusinessName(updateBusinessRequest.getBusinessName());
        business.setAddress(updateBusinessRequest.getAddress());
        businessRepository.save(business);
    }

    void addAdministratorToBusiness(AddAdministratorToBusinessCommand addAdministratorToBusinessRequest) {
        Optional<Business> businessOptional = businessRepository.findById(addAdministratorToBusinessRequest.getBusinessId());
        if (!businessOptional.isPresent()) {
            throw new ApiException("Cannot find business");
        }
        Business business = businessOptional.get();
        if (business.getAdministrators().contains(userFacade.getAuthenticatedUser())) {
            throw new ApiException("Cannot update business, permission denied");
        }

        User newAdministrator = userFacade.getUserByUsername(addAdministratorToBusinessRequest.getUsernameOfAdministrator());
        business.getAdministrators().add(newAdministrator);
        businessRepository.save(business);
    }

    void removeAdministratorFromBusiness(RemoveAdministratorCommand removeAdministratorCommand) {
        Optional<Business> businessOptional = businessRepository.findById(removeAdministratorCommand.getBusinessId());
        if (!businessOptional.isPresent()) {
            throw new ApiException("Cannot find business");
        }
        Business business = businessOptional.get();
        User authenticatedUser = userFacade.getAuthenticatedUser();
        if (business.getAdministrators().contains(authenticatedUser)) {
            throw new ApiException("Cannot update business, permission denied");
        }

        if (authenticatedUser.getEmail().equals(removeAdministratorCommand.getUsernameOfAdministrator()) ||
            authenticatedUser.getPhoneNumber().equals(removeAdministratorCommand.getUsernameOfAdministrator())) {
            throw new ApiException("Cannot remove yourself from business");
        }

        business.getAdministrators()
                .remove(userFacade.getUserByUsername(removeAdministratorCommand.getUsernameOfAdministrator()));
        businessRepository.save(business);

    }
}
