package com.nodaji.lotto_payment.domain.dto.response;

import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.entity.LottoResult;

import java.util.List;
import java.util.UUID;

public record LottoPaymentResponse(
        Long id,
        Integer first,
        Integer second,
        Integer third,
        Integer fourth,
        Integer fifth,
        Integer sixth,
        UUID userId
) {
    public List<Integer> makeLottoPaymentList(LottoPaymentResponse lottoPayment) {
        return List.of(
                lottoPayment.first(),
                lottoPayment.second(),
                lottoPayment.third(),
                lottoPayment.fourth(),
                lottoPayment.fifth(),
                lottoPayment.sixth()
        );
    }

    public int countLottoNumber(LottoPaymentResponse lottoPayment, LottoResultRequest req, LottoResult lottoResult) {
        List<Integer> lottoResults = req.makeLottoResultList(lottoResult);
        List<Integer> lottoPayments = makeLottoPaymentList(lottoPayment);

        int lottoWinCount = 0;
        for (Integer lotto : lottoResults) {
            if(lottoPayments.contains(lotto)) lottoWinCount++;
        }
        return lottoWinCount;
    }

    public boolean isBonusNumber(LottoPaymentResponse lottoPayment, int bonusNumber) {
        List<Integer> lottoPayments = makeLottoPaymentList(lottoPayment);
        return lottoPayments.contains(bonusNumber);
    }
}
