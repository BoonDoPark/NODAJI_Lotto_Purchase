package com.nodaji.lotto_payment.domain.dto.request;

public record LottoPayRequest(
        String type,
        Long amount
) {
    public static LottoPayRequest payRequest(String type, Long amount) {
        return new LottoPayRequest(type, amount);
    }
}
