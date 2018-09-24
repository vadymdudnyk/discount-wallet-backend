package com.vdudnyk.discountwallet.application.notification;

import com.vdudnyk.discountwallet.application.coupon.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationFacade {
    private final NotificationService notificationService;
    public void sendCouponCreatedNotification(Coupon coupon){
        notificationService.sendCouponCreatedNotification(coupon);
    }
}
