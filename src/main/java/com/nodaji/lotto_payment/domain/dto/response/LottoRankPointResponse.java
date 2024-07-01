package com.nodaji.lotto_payment.domain.dto.response;

public record LottoRankPointResponse(
        Long payId,
        String userId,
        Integer rank
) {
}
