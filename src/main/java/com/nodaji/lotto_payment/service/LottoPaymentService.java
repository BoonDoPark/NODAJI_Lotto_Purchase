package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.request.LottoPaymentRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;

import java.util.List;
import java.util.UUID;

public interface LottoPaymentService {
    void save(List<LottoPaymentRequest> requests);
    List<LottoPaymentResponse> getAllByUserIdAndRoundId(Long round);
}
