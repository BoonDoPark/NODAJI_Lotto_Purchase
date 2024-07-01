package com.nodaji.lotto_payment.domain.dto.request;

import com.nodaji.lotto_payment.domain.entity.LottoRankPoint;

public record LottoRankPointRequest(
        Long payId,
        String userId,
        Integer rank
) {
}
