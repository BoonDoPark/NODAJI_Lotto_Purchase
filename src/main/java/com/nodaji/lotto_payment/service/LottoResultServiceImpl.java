package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankResponse;
import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import com.nodaji.lotto_payment.utils.LottoRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
    private final LottoResultRepository lottoResultRepository;
    private final LottoPaymentRepository lottoPaymentRepository;

    @Override
    public void save(LottoResultRequest req) {
        if (req == null) throw new IllegalArgumentException();
        LottoResult lottoResult = lottoResultRepository.save(req.toEntity());
        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findByRound(lottoResult.getId());
        List<Map<UUID, Integer>> responseRank = new ArrayList<>();
        LottoRankResponse lottoRankResponse = new LottoRankResponse();

        lottoPaymentResponses.forEach(lottoPaymentResponse -> {
            int count = lottoPaymentResponse.countLottoNumber(lottoPaymentResponse, req, lottoResult);
            boolean isBonusCheck = lottoPaymentResponse.isBonusNumber(lottoPaymentResponse, req.bonus());
            LottoRank lottoRank = getRank(count, isBonusCheck);
            System.out.println(lottoRank);
            // 유저 별로 당첨 로직 필요
             responseRank.add(lottoRankResponse.makeMaxRankByUser(lottoPaymentResponse.userId(), lottoRank.getLottoRank()));
        });



        // 유저 ID, 최고 등수를 auth와 구매 내역으로 전달 로직 필요
//         responseRank
    }

    private LottoRank getRank(int countLottoNum, boolean isBonusMatch) {
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
