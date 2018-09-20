package com.vdudnyk.discountwallet.application.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFacade {
    private final CustomerService customerService;

    public void subscibeUserToBusiness(Long businessId) {
        customerService.subscribeUserToBusiness(businessId);
    }
}
