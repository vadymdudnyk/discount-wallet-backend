package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.coupon.CouponFacade;
import com.vdudnyk.discountwallet.application.coupon.shared.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponFacade codeFacade;

    @GetMapping("/businesses/{businessId}/coupons")
    List<CouponDTO> getAllBusinessCoupons(@PathVariable Long businessId) {
        return codeFacade.getAllBusinessCoupons(businessId);
    }

    @GetMapping("/business/{businessId}/codes/{code}/validate")
    ResponseEntity<StatusResponse> validateCode(@PathVariable Long businessId, @PathVariable String code) {
        codeFacade.validateCoupon(businessId, code);
        return ResponseEntity.ok(StatusResponse.success());
    }
}


