package com.vdudnyk.discountwallet.application.coupon.shared;

import com.vdudnyk.discountwallet.application.business.shared.CustomerDTO;
import com.vdudnyk.discountwallet.application.coupon.CouponType;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CouponDTO {
    private Long id;
    private String value;
    private CustomerDTO customer;
    private Long campaignId;
    private CouponType couponType;
    private Long usages;
    private Long maxUsages;
    private Boolean active;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
}
