package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.coupon.Coupon;
import com.vdudnyk.discountwallet.application.coupon.CouponFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CodeController {
    private final CouponFacade codeFacade;

    @GetMapping("/businesses/{businessId}/codes")
    List<Coupon> getAllBusinessCodes(@PathVariable Long businessId) {
        return codeFacade.getAllBusinessCoupones(businessId);
    }

    @GetMapping("/business/{businessId}/codes/{code}/validate")
    ResponseEntity<StatusResponse> validateCode(@PathVariable Long businessId, @PathVariable String code) {
        codeFacade.validateCoupon(businessId, code);
        return ResponseEntity.ok(StatusResponse.success());
    }
}


