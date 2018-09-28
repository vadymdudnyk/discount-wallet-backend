package com.vdudnyk.discountwallet.application.coupon;

import com.vdudnyk.discountwallet.application.business.BusinessFacade;
import com.vdudnyk.discountwallet.application.business.shared.CustomerDTO;
import com.vdudnyk.discountwallet.application.coupon.shared.CouponDTO;
import com.vdudnyk.discountwallet.application.coupon.shared.CreateCouponRequest;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class CouponService {
    private final CouponRepository couponRepository;
    private final CodeGenerator codeGenerator;
    private final BusinessFacade businessFacade;
    private final UserFacade userFacade;

    List<CouponDTO> getAllBusinessCoupons(Long businessId) {
        return couponRepository.findAllByBusinessId(businessId)
                               .stream()
                               .map(coupon -> new CouponDTO(
                                       coupon.getId(),
                                       coupon.getValue(),
                                       new CustomerDTO(
                                               coupon.getUser().getId(),
                                               coupon.getUser().getEmail(),
                                               coupon.getUser().getPhoneNumber(),
                                               coupon.getUser().getFirstName(),
                                               coupon.getUser().getLastName()
                                       ),
                                       coupon.getCampaignId(),
                                       coupon.getCouponType(),
                                       coupon.getUsages(),
                                       coupon.getMaxUsages(),
                                       coupon.getActive(),
                                       coupon.getDescription(),
                                       coupon.getCreationDate(),
                                       coupon.getExpirationDate()
                               ))
                               .collect(Collectors.toList());
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
