package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.coupon.shared.CustomerCouponDTO;
import com.vdudnyk.discountwallet.application.customer.CustomerFacade;
import com.vdudnyk.discountwallet.application.customer.dto.DiscoveredBusinessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerFacade customerFacade;

    @PostMapping("/businesses/{businessId}/subscribers")
    private ResponseEntity<StatusResponse> subscribeToBusiness(@PathVariable Long businessId) {
        customerFacade.subscribeUserToBusiness(businessId);
        return ResponseEntity.ok(StatusResponse.success());
    }

    @GetMapping("/businesses/discover")
    private ResponseEntity<List<DiscoveredBusinessDTO>> discoverBusinesses() {
        List<DiscoveredBusinessDTO> businesses = customerFacade.discoverBusinesses();
        return ResponseEntity.ok(businesses);
    }

    @GetMapping("/customer/coupons")
    private ResponseEntity<List<CustomerCouponDTO>> getCustomerCoupons() {
        return ResponseEntity.ok(customerFacade.getCustomerCoupons());
    }
}
