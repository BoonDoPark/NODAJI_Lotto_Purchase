package com.nodaji.lotto_payment.domain.dto.request;

import java.time.LocalDate;

public record KafkaPayInfoRequest(
        Long id,
        String userId,
        LocalDate createAt,
        Long round
) {
}
