package com.vdudnyk.discountwallet.application.customer;

import com.vdudnyk.discountwallet.application.coupon.shared.CustomerCouponDTO;
import com.vdudnyk.discountwallet.application.customer.dto.DiscoveredBusinessDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerFacade {
    private final CustomerService customerService;

    public void subscribeUserToBusiness(Long businessId) {
        customerService.subscribeUserToBusiness(businessId);
    }

    public List<DiscoveredBusinessDTO> discoverBusinesses() {
        return customerService.discoverBusinesses();
    }

    public List<CustomerCouponDTO> getCustomerCoupons() {
        return customerService.getCustomerCoupons();
    }
}
