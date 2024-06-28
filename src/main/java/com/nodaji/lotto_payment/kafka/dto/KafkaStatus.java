package com.nodaji.lotto_payment.kafka.dto;

public record KafkaStatus<T>(
        T data, String status
) {
}
