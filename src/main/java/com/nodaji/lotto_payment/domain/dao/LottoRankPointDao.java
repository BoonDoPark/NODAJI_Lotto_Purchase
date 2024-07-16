package com.nodaji.lotto_payment.domain.dao;

import com.nodaji.lotto_payment.domain.dto.request.LottoRankPointRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;

import java.util.List;

public interface LottoRankPointDao {
    void savePoint(Integer rank, Long amount, Long payId);
    void saveNotRank(Long paymentId, String userId, Long resultId);
    void saveRank(Long paymentId, String userId, Long resultId, Integer rank);
    List<LottoRankPointResponse> getRank(Integer rank);
}
