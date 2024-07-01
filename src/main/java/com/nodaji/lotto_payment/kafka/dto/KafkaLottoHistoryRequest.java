package com.nodaji.lotto_payment.kafka.dto;

public record KafkaLottoHistoryRequest(
       Long payId,
        Long amount,
        Integer rank
) {
}
