package com.vdudnyk.discountwallet.application.coupon.shared;

import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCouponRequest {
    private Long businessId;
    private Long userId;
    private Long campaignId;
    private CouponType couponType;
    private Long usages;
    private Long maxUsages;
    private Boolean active;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
}
