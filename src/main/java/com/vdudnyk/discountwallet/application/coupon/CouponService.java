package com.vdudnyk.discountwallet.application.coupon;

import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.coupon.shared.CreateCouponRequest;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class CouponService {
    private final CouponRepository couponRepository;
    private final CodeGenerator codeGenerator;
    private final BusinessFacade businessFacade;
    private final UserFacade userFacade;

    List<Coupon> getAllBusinessCoupons(Long businessId) {
        return couponRepository.findAllByBusiness(businessId);
    }

    void validateCode(Long businessId, String code) {
        log.info("businessId: {}, code: {}", businessId, code);
    }

    Coupon createCoupon(CreateCouponRequest createCouponRequest) {
        Coupon coupon = new Coupon();
        coupon.setActive(createCouponRequest.getActive());
        coupon.setBusiness(businessFacade.getBusiness(createCouponRequest.getBusinessId()));
        coupon.setCouponType(createCouponRequest.getCouponType());
        coupon.setCreationDate(createCouponRequest.getCreationDate());
        coupon.setExpirationDate(createCouponRequest.getExpirationDate());
        coupon.setDescription(createCouponRequest.getDescription());
        coupon.setMaxUsages(createCouponRequest.getMaxUsages());
        coupon.setUsages(0L);
        coupon.setUser(userFacade.getUserById(createCouponRequest.getUserId()));
        coupon.setCampaignId(createCouponRequest.getCampaignId());
        coupon.setValue(codeGenerator.generateSimpleCode());
        return couponRepository.save(coupon);
    }
}
