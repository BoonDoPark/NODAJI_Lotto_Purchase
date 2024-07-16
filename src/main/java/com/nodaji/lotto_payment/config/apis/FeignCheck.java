package com.nodaji.lotto_payment.config.apis;

import com.nodaji.lotto_payment.domain.dto.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accounts-service", url = "34.46.237.231:30602")
public interface FeignCheck {

    @PostMapping("api/v1/lottery")
    void sendPoint(@RequestBody PointRequest req);
}
