package com.nodaji.lotto_payment.domain.dto.request;

public record KafkaLottoRankRequest(
        String userId,
        String game,
        Long round,
        int rank
) {
}
