package com.vdudnyk.discountwallet.application.coupon.shared;

import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CustomerCouponDTO {
    private Long id;
    private String value;
    private CouponType couponType;
    private Long usages;
    private Long maxUsages;
    private Boolean active;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private String businessName;
}
