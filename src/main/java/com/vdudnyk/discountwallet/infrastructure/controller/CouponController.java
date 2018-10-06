package com.vdudnyk.discountwallet.infrastructure.controller;

import com.vdudnyk.discountwallet.application.coupon.CouponFacade;
import com.vdudnyk.discountwallet.application.coupon.shared.CouponDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponFacade codeFacade;

    @GetMapping("/businesses/{businessId}/coupons")
    List<CouponDTO> getAllBusinessCoupons(@PathVariable Long businessId) {
        return codeFacade.getAllBusinessCoupons(businessId);
    }

    @GetMapping("/validate/{code}")
    ResponseEntity<StatusResponse> validateCode(@PathVariable String code) {
        log.info("Validating code:{}", code);
        return ResponseEntity.ok(StatusResponse.success());
    }

    @GetMapping(value = "/qrCodes/{couponId}",
                produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<byte[]> getQrCode(@PathVariable Long couponId) {
        File file = codeFacade.getQRCode(couponId);
        try {
            byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream(file));
            return ResponseEntity.ok(bytes);
        } catch (IOException e) {
            log.error("Couldn't send coupon qr code: {}", couponId, e);
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}


