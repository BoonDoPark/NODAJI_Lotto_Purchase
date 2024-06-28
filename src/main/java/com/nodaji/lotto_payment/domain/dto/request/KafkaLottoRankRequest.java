package com.nodaji.lotto_payment.domain.dto.request;

public record KafkaLottoRankRequest(
        String id,
        String game,
        Long round,
        int rank
) {
}
