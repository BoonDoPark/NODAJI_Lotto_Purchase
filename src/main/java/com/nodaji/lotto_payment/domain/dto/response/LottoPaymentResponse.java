package com.nodaji.lotto_payment.domain.dto.response;

public record LottoPaymentResponse(
        Long id,
        Integer first,
        Integer second,
        Integer third,
        Integer fourth,
        Integer fifth,
        Integer sixth
) {
}
