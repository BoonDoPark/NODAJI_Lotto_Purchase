package com.nodaji.lotto_payment.utils;

public enum LottoRank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int lottoRank;

    LottoRank(int i) {
        this.lottoRank = i;
    }

    public int getLottoRank() {
        return lottoRank;
    }
}
