package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;

import java.util.List;
import java.util.Map;

public interface ApiService {
    void notifyRankForAuth(Map<String, Integer> responseRank, Long round);
    void notifyTopRankForUser(List<LottoRankPointResponse> rankPointResponses, long totalPoints, int rank);
    void notifyDownRankForUser(List<LottoRankPointResponse> rankPointResponses, long point, int rank);
}
