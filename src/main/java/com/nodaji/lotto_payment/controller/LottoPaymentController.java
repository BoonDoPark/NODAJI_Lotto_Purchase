package com.nodaji.lotto_payment.controller;

import com.nodaji.lotto_payment.domain.dto.request.LottoPaymentRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.service.LottoPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lotto/pay")
public class LottoPaymentController {
    private final LottoPaymentService lottoPaymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePayment(@RequestBody List<LottoPaymentRequest> requests) {
        lottoPaymentService.save(requests);
    }

    @GetMapping
    public List<LottoPaymentResponse> getAllByRound(@Param("round") Long round) {
        return lottoPaymentService.getAllByUserIdAndRoundId(round);
    }
}
