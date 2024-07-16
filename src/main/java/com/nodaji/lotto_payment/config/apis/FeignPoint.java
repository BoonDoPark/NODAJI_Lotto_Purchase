package com.nodaji.lotto_payment.config.apis;

import com.nodaji.lotto_payment.domain.dto.request.LottoPayRequest;
import com.nodaji.lotto_payment.domain.dto.response.PointResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "accounts-service", url = "34.46.237.231:30421")
public interface FeignPoint {

    @GetMapping("api/v1/accounts/{userId}")
    PointResponse getPoint(@PathVariable("userId") String userId);

    @PostMapping("api/v1/payments/{userId}")
    void subtractPoint(@PathVariable("userId") String userId, @RequestBody LottoPayRequest req);
}
