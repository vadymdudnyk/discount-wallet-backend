package com.vdudnyk.discountwallet.application.coupon;

import com.vdudnyk.discountwallet.application.coupon.shared.CouponDTO;
import com.vdudnyk.discountwallet.application.coupon.shared.CreateCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponFacade {
    private final CouponService couponService;

    public List<CouponDTO> getAllBusinessCoupons(Long businessId) {
        return couponService.getAllBusinessCoupons(businessId);
    }

    public void validateCoupon(Long businessId, String code) {
        couponService.validateCode(businessId, code);
    }

    public Coupon createCoupon(CreateCouponRequest createCouponRequest) {
        return couponService.createCoupon(createCouponRequest);
    }

}
