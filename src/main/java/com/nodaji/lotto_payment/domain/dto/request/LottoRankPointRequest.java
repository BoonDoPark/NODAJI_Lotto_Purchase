package com.nodaji.lotto_payment.domain.dto.request;

import com.nodaji.lotto_payment.domain.entity.LottoRankPoint;

public record LottoRankPointRequest(
        Long payId,
        String userId,
        Long round,
        Integer rank
) {
    public LottoRankPoint toEntity() {
        return LottoRankPoint.builder()
                .payId(payId)
                .userId(userId)
                .round(round)
                .rank(rank)
                .build();
    }
}
