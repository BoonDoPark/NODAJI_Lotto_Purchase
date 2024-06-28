package com.nodaji.lotto_payment.domain.dto.request;

import com.nodaji.lotto_payment.domain.entity.LottoPayment;

import java.time.LocalDate;

public record LottoPaymentRequest(
        Integer first,
        Integer second,
        Integer third,
        Integer fourth,
        Integer fifth,
        Integer sixth
) {
    public LottoPayment toEntity(Long round, String userId) {
        return LottoPayment.builder()
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
                .sixth(sixth)
                .round(round)
                .userId(userId)
                .createAt(LocalDate.now())
                .build();
    }
}
