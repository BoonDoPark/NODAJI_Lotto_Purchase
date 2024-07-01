package com.nodaji.lotto_payment.service;

import java.util.List;
import java.util.Map;

public interface TotalPointService {
    void matchRank(Map<Integer, List<String>> lottoResultPoint, int rank, Long totalPoint, Long lottoPoint);
}
