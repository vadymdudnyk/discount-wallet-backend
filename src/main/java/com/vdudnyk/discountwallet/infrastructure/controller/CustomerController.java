package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.customer.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerFacade customerFacade;

    @PostMapping("/businesses/{businessId}/subscribers")
    private void subscribeToBusiness(@PathVariable Long businessId) {
        customerFacade.subscibeUserToBusiness(businessId);
    }
}
