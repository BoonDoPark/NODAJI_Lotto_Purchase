package com.nodaji.lotto_payment.domain.dao;

import com.nodaji.lotto_payment.domain.dto.request.LottoRankPointRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;
import com.nodaji.lotto_payment.domain.entity.LottoRankPoint;
import com.nodaji.lotto_payment.domain.repository.LottoRankPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LottoRankPointDaoImpl implements LottoRankPointDao{
    private final LottoRankPointRepository lottoRankPointRepository;

    @Override
    public void savePoint(Integer rank, Long amount, Long payId) {
        LottoRankPoint lottoRankPoint = lottoRankPointRepository.findByRankAndPayId(rank, payId);
        lottoRankPoint.setPoint(amount);
        lottoRankPointRepository.save(lottoRankPoint);
    }

    @Override
    public void saveNotRank(Long paymentId, String userId, Long resultId) {
        lottoRankPointRepository.save(LottoRankPoint.builder()
                .payId(paymentId)
                .userId(userId)
                .round(resultId)
                .build());
    }

    @Override
    public void saveRank(Long paymentId, String userId, Long resultId, Integer rank) {
        lottoRankPointRepository.save(LottoRankPoint.builder()
                .payId(paymentId)
                .userId(userId)
                .round(resultId)
                .rank(rank)
                .build());
    }

    @Override
    public List<LottoRankPointResponse> getRank(Integer rank) {
        List<LottoRankPointResponse> lottoRankPointResponses = lottoRankPointRepository.findByRank(rank);
        if (lottoRankPointResponses.isEmpty()) throw new IllegalArgumentException();
        return lottoRankPointResponses;
    }
}
