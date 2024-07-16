package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.request.LottoPaymentRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;

import java.util.List;

public interface LottoPaymentService {
    void save(String userId, List<LottoPaymentRequest> requests);
    List<LottoPaymentResponse> getAllByUserIdAndRoundId(Long round);
}
