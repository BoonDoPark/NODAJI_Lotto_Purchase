package com.nodaji.lotto_payment.config.utils;

import lombok.Getter;

@Getter
public enum LottoRank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    NOT(6);

    private final int lottoRank;

    LottoRank(int i) {
        this.lottoRank = i;
    }

    public static LottoRank getRank(int countLottoNum, boolean isBonusMatch) {
        if (countLottoNum == 6) {
            return LottoRank.ONE;
        } else if (countLottoNum == 5 && isBonusMatch) {
            return LottoRank.TWO;
        } else if (countLottoNum == 5) {
            return LottoRank.THREE;
        } else if (countLottoNum == 4) {
            return LottoRank.FOUR;
        } else if (countLottoNum == 3) {
            return LottoRank.FIVE;
        } else return LottoRank.NOT;
    }
}
