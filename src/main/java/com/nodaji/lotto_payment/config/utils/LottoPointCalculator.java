package com.nodaji.lotto_payment.config.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoPointCalculator {

    public Long fifthTotalPoint(Map<Integer, List<String>> lottoResultPoint, long totalPoint) {
        return lottoResultPoint.getOrDefault(5, new ArrayList<>()).size() * LottoPoint.FIFTH.getLottoPoint();
    }
}
