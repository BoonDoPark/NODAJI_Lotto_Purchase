package com.nodaji.lotto_payment.controller;

import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.service.LottoResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lotto/result")
public class LottoResultController {
    private final LottoResultService lottoResultService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saverResult(@RequestBody LottoResultRequest req) {
        lottoResultService.save(req);
    }
}
