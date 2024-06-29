package com.nodaji.lotto_payment.kafka.dto;

public record KafkaPayStatus<T>(
        T data, String status
) {
}
