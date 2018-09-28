package com.vdudnyk.discountwallet.application.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByBusinessId(Long businessId);
}
