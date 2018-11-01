package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.coupon.CouponType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/enums")
public class EnumController {
    @GetMapping("/coupon-types")
    ResponseEntity<List<CouponType>> getCouponTypes() {
        return ResponseEntity.ok(asList(CouponType.values()));
    }
}
