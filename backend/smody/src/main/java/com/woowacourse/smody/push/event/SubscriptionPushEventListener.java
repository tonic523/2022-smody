package com.woowacourse.smody.push.event;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.woowacourse.smody.member.domain.Member;
import com.woowacourse.smody.push.domain.PushCase;
import com.woowacourse.smody.push.domain.PushNotification;
import com.woowacourse.smody.push.domain.PushStatus;
import com.woowacourse.smody.push.domain.PushSubscribeEvent;
import com.woowacourse.smody.push.domain.PushSubscription;
import com.woowacourse.smody.push.service.PushNotificationService;
import com.woowacourse.smody.push.service.WebPushService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubscriptionPushEventListener {

    private final PushNotificationService pushNotificationService;
    private final WebPushService webPushService;

    @Transactional
    @Async("asyncExecutor")
    @TransactionalEventListener
    public void handle(PushSubscribeEvent event) {
        PushSubscription pushSubscription = event.getPushSubscription();

        Member member = pushSubscription.getMember();
        PushNotification pushNotification = pushNotificationService.register(buildNotification(member));

        webPushService.sendNotification(pushSubscription, pushNotification);
    }

    public PushNotification buildNotification(Object entity) {
        Member member = (Member) entity;
        return PushNotification.builder()
                .message(member.getNickname() + "님 스모디 알림이 구독되었습니다.")
                .pushTime(LocalDateTime.now())
                .pushStatus(PushStatus.COMPLETE)
                .member(member)
                .pushCase(PushCase.SUBSCRIPTION)
                .build();
    }
}