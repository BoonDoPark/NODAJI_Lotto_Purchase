package com.nodaji.lotto_payment.domain.dto.request;

public record PointRequest(
        String userId,
        Long amount
) {
}
