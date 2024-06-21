package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoPayment;
import com.nodaji.lotto_payment.utils.LottoRank;

public interface LottoResultService {
    void save(LottoResultRequest req);
    int countLottoNumber(LottoPaymentResponse lottoPayment, LottoResultRequest lottoResult);
    LottoRank getRank(int countLottoNum);
}
