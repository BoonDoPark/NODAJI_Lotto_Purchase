package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.config.utils.LottoPoint;
import com.nodaji.lotto_payment.config.utils.LottoRank;
import com.nodaji.lotto_payment.domain.dao.LottoRankPointDao;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;
import com.nodaji.lotto_payment.domain.repository.TotalPointRepository;
import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
    private final LottoResultRepository lottoResultRepository;
    private final LottoPaymentRepository lottoPaymentRepository;
    private final TotalPointRepository totalPointRepository;
    private final LottoRankPointDao lottoRankPointDao;
    private final ApiService apiService;

    @Override
    @Transactional
    public void save(LottoResultRequest req) {
         if (req == null) throw new IllegalArgumentException();

         // 로또 등수 정하기
        LottoResult lottoResult = lottoResultRepository.save(req.toEntity());
        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findByRound(lottoResult.getId());
        Map<String, Integer> responseRank = processLottoPayments(lottoPaymentResponses, req, lottoResult);

        Long totalPoint = totalPointRepository.findByRound(lottoResult.getId()).getTotalPoint();
        if (totalPoint == null) throw new IllegalArgumentException("Not Total Point");

        // 등수별 포인트 지급
        distributePoints(totalPoint);
        apiService.notifyRankForAuth(responseRank, lottoResult.getId());
    }

    private Map<String, Integer> processLottoPayments(List<LottoPaymentResponse> lottoPaymentResponses, LottoResultRequest req, LottoResult lottoResult) {
        Map<String, Integer> responseRank = new HashMap<>();

        for (LottoPaymentResponse lottoPaymentResponse : lottoPaymentResponses) {
            int count = lottoPaymentResponse.countLottoNumber(lottoPaymentResponse, req, lottoResult);
            boolean isBonusCheck = lottoPaymentResponse.isBonusNumber(lottoPaymentResponse, req.bonus());
            LottoRank lottoRank = LottoRank.getRank(count, isBonusCheck);
            if (lottoRank == null) {
                lottoRankPointDao.saveNotRank(lottoPaymentResponse.id(), lottoPaymentResponse.userId(), lottoResult.getId());
                continue;
            }
            lottoRankPointDao.saveRank(lottoPaymentResponse.id(), lottoPaymentResponse.userId(), lottoResult.getId(), lottoRank.getLottoRank());
            responseRank.put(lottoPaymentResponse.userId(), lottoRank.getLottoRank());
        }
        return responseRank;
    }

    private void distributePoints(Long totalPoint) {
        List<LottoRankPointResponse> rankPointResponses;

        // 5등 포인트 분배
        rankPointResponses = lottoRankPointDao.getRank(5);
        apiService.notifyDownRankForUser(rankPointResponses, LottoPoint.FIFTH.getLottoPoint(), 5);
        totalPoint -= rankPointResponses.size() * LottoPoint.FIFTH.getLottoPoint();

        // 4등 포인트 분배
        rankPointResponses = lottoRankPointDao.getRank(4);
        apiService.notifyDownRankForUser(rankPointResponses, LottoPoint.FOURTH.getLottoPoint(), 4); // Assuming 4th place points are the same as 5th place
        totalPoint -= rankPointResponses.size() * LottoPoint.FOURTH.getLottoPoint();

        // 남은 포인트 계산
        long thirdTotalPoint = (long) (totalPoint * 0.12);
        long secondTotalPoint = (long) (totalPoint * 0.13);
        long firstTotalPoint = totalPoint - (thirdTotalPoint + secondTotalPoint); // 나머지 포인트 모두 1등에 할당

        // 3등 포인트 분배
        rankPointResponses = lottoRankPointDao.getRank(3);
        apiService.notifyTopRankForUser(rankPointResponses, thirdTotalPoint, 3);

        // 2등 포인트 분배
        rankPointResponses = lottoRankPointDao.getRank(2);
        apiService.notifyTopRankForUser(rankPointResponses, secondTotalPoint, 2);

        // 1등 포인트 분배
        rankPointResponses = lottoRankPointDao.getRank(1);
        apiService.notifyTopRankForUser(rankPointResponses, firstTotalPoint, 1);
    }
}
