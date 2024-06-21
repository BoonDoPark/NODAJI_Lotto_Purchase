package com.nodaji.lotto_payment.controller;

import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.service.LottoResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lotto/result")
public class LottoResultController {
    private final LottoResultService lottoResultService;

    @PostMapping
    public void saverResult(@RequestBody LottoResultRequest req) {
        lottoResultService.save(req);
    }
}
