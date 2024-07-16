package com.nodaji.lotto_payment.config.utils;

import lombok.Getter;

@Getter
public enum LottoPoint {

    FIFTH(5000L),
    FOURTH(50000L);

    private final Long lottoPoint;

    LottoPoint(long point) {
        this.lottoPoint = point;
    }

    public static Long point(int rank) {
        if (rank == 5) {
            return LottoPoint.FIFTH.getLottoPoint();
        } else if (rank == 4) {
            return LottoPoint.FOURTH.getLottoPoint();
        } else return null;
    }
}
