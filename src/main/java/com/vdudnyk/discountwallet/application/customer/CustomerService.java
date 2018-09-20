package com.vdudnyk.discountwallet.application.customer;

import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.code.CodeFacade;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final UserFacade userFacade;
    private final BusinessFacade businessFacade;
    private final CodeFacade codeFacade;

    void subscribeUserToBusiness(Long businessId) {
        User authenticatedUser = userFacade.getAuthenticatedUser();
        businessFacade.addCustomer(authenticatedUser, businessId);
        //welcome codes generate here
    }
}
