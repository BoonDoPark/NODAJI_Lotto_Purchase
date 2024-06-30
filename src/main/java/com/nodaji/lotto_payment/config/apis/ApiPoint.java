package com.nodaji.lotto_payment.config.apis;

import com.nodaji.lotto_payment.domain.dto.request.KafkaPayInfoRequest;
import com.nodaji.lotto_payment.domain.dto.request.LottoPayRequest;
import com.nodaji.lotto_payment.domain.dto.request.PointRequest;
import com.nodaji.lotto_payment.domain.dto.response.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiPoint {
    private final FeignPoint feignPoint;
    private final FeignPoint1 feignPoint1;

    @Async
    public PointResponse getPoint(String userId) {
        return feignPoint.getPoint(userId);
    }

    @Async
    public void sendPoint(PointRequest pointRequest) {
        feignPoint.sendPoint(pointRequest);
    }

    @Async
    public void subtractPoint(String userId, LottoPayRequest req) {
        feignPoint.subtractPoint(userId, req);
    }

    public void createResult(KafkaPayInfoRequest kafkaPayInfoRequest) { feignPoint1.createResult(kafkaPayInfoRequest);}
}
