package com.vdudnyk.discountwallet.application.business;

import com.vdudnyk.discountwallet.application.business.shared.*;
import com.vdudnyk.discountwallet.application.event.Event;
import com.vdudnyk.discountwallet.application.event.EventFacade;
import com.vdudnyk.discountwallet.application.loyaltycard.LoyaltyCardPolicy;
import com.vdudnyk.discountwallet.application.loyaltycard.LoyaltyCardPolicyFacade;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.CreateLoyaltyCardPolicyCommand;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.UpdateLoyaltyCardPolicy;
import com.vdudnyk.discountwallet.application.shared.ApiException;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserFacade userFacade;
    private final EventFacade eventFacade;
    private final LoyaltyCardPolicyFacade loyaltyCardPolicyFacade;

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
        Business savedBusiness = businessRepository.save(business);

        loyaltyCardPolicyFacade.createLoyaltyCardPolicy(new CreateLoyaltyCardPolicyCommand(
                savedBusiness.getId(),
                5L,
                "Give free coffee for that guy"
        ));
    }

    void updateBusiness(UpdateBusinessCommand updateBusinessRequest) {
        Business business = getBusinessById(updateBusinessRequest.getId());

        validateBusinessOwnership(business);

        business.setZipCode(updateBusinessRequest.getZipCode());
        business.setCity(updateBusinessRequest.getCity());
        business.setBusinessName(updateBusinessRequest.getBusinessName());
        business.setAddress(updateBusinessRequest.getAddress());
        businessRepository.save(business);
    }

    void addAdministratorToBusiness(AddAdministratorToBusinessCommand addAdministratorToBusinessRequest) {
        Business business = getBusinessById(addAdministratorToBusinessRequest.getBusinessId());

        validateBusinessOwnership(business);

        User newAdministrator = userFacade.getUserByUsername(addAdministratorToBusinessRequest.getUsernameOfAdministrator());
        business.getAdministrator().add(newAdministrator);
        businessRepository.save(business);
    }

    void removeAdministratorFromBusiness(RemoveAdministratorCommand removeAdministratorCommand) {
        Business business = getBusinessById(removeAdministratorCommand.getBusinessId());
        User authenticatedUser = userFacade.getAuthenticatedUser();
        validateBusinessOwnership(business);
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

    List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    List<CustomerDTO> getBusinessCustomers(Long businessId) {
        return getBusinessById(businessId)
                .getCustomer()
                .stream()
                .map(user -> new CustomerDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getFirstName(),
                        user.getLastName()
                ))
                .collect(Collectors.toList());
    }

    List<BusinessEventDTO> getBusinessEvents(Long businessId) {
        Business business = getBusinessById(businessId);
        validateBusinessOwnership(business);
        List<Event> businessEvents = eventFacade.findByBusinessId(businessId);
        return businessEvents.stream()
                             .map(event -> new BusinessEventDTO(
                                     event.getCreatedAt(),
                                     event.getEventType(),
                                     event.getDescription()
                                          .replace("${userId}", userFacade.getUserById(event.getUserId()).getEmail())
                                          .replace("${businessId}", getBusinessById(event.getBusinessId()).getBusinessName())
                             ))
                             .collect(Collectors.toList());
    }

    private Business getBusinessById(Long businessId) {
        return businessRepository.findById(businessId).orElseThrow(() -> new ApiException("Business not found"));
    }

    private void validateBusinessOwnership(Business business) {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        if (!business.getAdministrator().contains(authenticatedUser)) {
            throw new ApiException("Permission denied");
        }
    }

    LoyaltyCardPolicy getLoyaltyCardPolicy(Long businessId) {
        Business businessById = getBusinessById(businessId);
        validateBusinessOwnership(businessById);
        return loyaltyCardPolicyFacade.getLoyaltyCardPolicy(businessId);
    }

    public void updateLoyaltyCardPolicy(UpdateLoyaltyCardPolicy updateLoyaltyCardPolicy) {
        Business businessById = getBusinessById(updateLoyaltyCardPolicy.getBusinessId());
        validateBusinessOwnership(businessById);
        loyaltyCardPolicyFacade.updateLoyaltyCardPolicy(updateLoyaltyCardPolicy);
    }
}
