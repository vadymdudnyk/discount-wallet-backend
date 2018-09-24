package com.vdudnyk.discountwallet.application.notification;

import com.vdudnyk.discountwallet.application.coupon.Coupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class NotificationService {

    void sendCouponCreatedNotification(Coupon coupon) {
        log.info("Sending notification to user: {}w", coupon.getUser().getEmail());
    }
}
