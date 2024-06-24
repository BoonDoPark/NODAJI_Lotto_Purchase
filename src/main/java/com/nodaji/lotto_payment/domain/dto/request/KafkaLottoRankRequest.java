package com.nodaji.lotto_payment.domain.dto.request;

import java.util.UUID;

public record KafkaLottoRankRequest(
        String userId,
        String name,
        int rank,
        String game,
        String email
) {

}
