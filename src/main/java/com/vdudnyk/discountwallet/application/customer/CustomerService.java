package com.vdudnyk.discountwallet.application.customer;

import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.coupon.CouponFacade;
import com.vdudnyk.discountwallet.application.coupon.shared.CustomerCouponDTO;
import com.vdudnyk.discountwallet.application.customer.dto.DiscoveredBusinessDTO;
import com.vdudnyk.discountwallet.application.event.EventFactory;
import com.vdudnyk.discountwallet.application.event.EventSender;
import com.vdudnyk.discountwallet.application.event.payload.UserSubscribedToBusinessEventPayload;
import com.vdudnyk.discountwallet.application.loyaltycard.LoyaltyCardFacade;
import com.vdudnyk.discountwallet.application.loyaltycard.shared.CustomerLoyaltyCardDTO;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserFacade userFacade;
    private final BusinessFacade businessFacade;
    private final CouponFacade couponFacade;
    private final LoyaltyCardFacade loyaltyCardFacade;
    private final EventSender eventSender;

    void subscribeUserToBusiness(Long businessId) {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        log.info("Subscribing user: {} to business: {}", authenticatedUser.getEmail(), businessId);
        businessFacade.addCustomer(authenticatedUser, businessId);
        //welcome codes generate here

        eventSender.send(EventFactory.createUserSubscribedToBusinessEvent(
                authenticatedUser.getId(),
                businessId,
                new UserSubscribedToBusinessEventPayload(
                        authenticatedUser.getEmail(),
                        authenticatedUser.getPhoneNumber())));
    }

    List<DiscoveredBusinessDTO> discoverBusinesses() {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        return businessFacade.getAllBusinesses()
                             .stream()
                             .map(business -> new DiscoveredBusinessDTO(
                                     business.getId(),
                                     business.getBusinessName(),
                                     business.getCity(),
                                     business.getAddress(),
                                     business.getZipCode(),
                                     business.getCustomer().contains(authenticatedUser)
                             ))
                             .collect(Collectors.toList());
    }

    List<CustomerCouponDTO> getCustomerCoupons() {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        return couponFacade.getCustomerCoupons(authenticatedUser.getId());
    }

    List<CustomerLoyaltyCardDTO> getCustomerLoyaltyCards() {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        return loyaltyCardFacade.getUsersLoyaltyCards(authenticatedUser.getId());
    }
}
