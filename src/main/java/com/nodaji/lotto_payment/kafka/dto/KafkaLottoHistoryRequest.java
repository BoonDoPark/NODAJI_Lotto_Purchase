package com.nodaji.lotto_payment.kafka.dto;

public record KafkaLottoHistoryRequest(
        String userId,
        Long amount,
        Long rank
) {
}
