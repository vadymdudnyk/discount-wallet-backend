package com.vdudnyk.discountwallet.application.campaign;

import com.vdudnyk.discountwallet.application.coupon.Coupon;
import com.vdudnyk.discountwallet.application.coupon.CouponFacade;
import com.vdudnyk.discountwallet.application.coupon.shared.CreateCouponRequest;
import com.vdudnyk.discountwallet.application.notification.NotificationFacade;
import com.vdudnyk.discountwallet.application.user.User;
import com.vdudnyk.discountwallet.application.user.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
class CampaignExecutorService {
    private final UserFacade userFacade;
    private final CampaignFacade campaignFacade;
    private final CouponFacade couponFacade;
    private final NotificationFacade notificationFacade;

    void executeWelcomeCampaigns(Long businessId, Long userId) {
        User user = userFacade.getUserById(userId);
        List<Campaign> campaigns = campaignFacade.getAllCampaignsByBusinessId(businessId);

        campaigns.stream()
                 .filter(campaign -> campaign.getCampaignType() == CampaignType.WELCOME)
                 .forEach(campaign -> executeCampaign(campaign, user));
    }

    private void executeCampaign(Campaign campaign, User user) {
        CreateCouponRequest createCouponRequest = new CreateCouponRequest();
        createCouponRequest.setActive(true);
        createCouponRequest.setBusinessId(campaign.getBusiness().getId());
        createCouponRequest.setCouponType(campaign.getCouponType());
        createCouponRequest.setDescription(campaign.getDescription());
        createCouponRequest.setCreationDate(LocalDateTime.now());
        createCouponRequest.setExpirationDate(LocalDateTime.now().plus(campaign.getExpirationTime(), HOURS));
        createCouponRequest.setMaxUsages(campaign.getMaxUsages());
        createCouponRequest.setUserId(user.getId());
        createCouponRequest.setCampaignId(campaign.getId());
        Coupon coupon = couponFacade.createCoupon(createCouponRequest);
        notificationFacade.sendCouponCreatedNotification(coupon);
    }
}
