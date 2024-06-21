package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoPayment;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import com.nodaji.lotto_payment.utils.LottoRank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
    private final LottoResultRepository lottoResultRepository;
    private final LottoPaymentRepository lottoPaymentRepository;

    @Override
    public void save(LottoResultRequest req) {
        if (req == null) throw new IllegalArgumentException();
        lottoResultRepository.save(req.toEntity());
        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findAllByRound(req.round());
        lottoPaymentResponses.forEach(userLotto -> {

        });
    }

    @Override
    public int countLottoNumber(LottoPaymentResponse lottoPayment, LottoResultRequest lottoResult) {
        int lottoWinCount = 0;
        if (lottoResult.first().equals(lottoPayment.first())) lottoWinCount++;
        if (lottoResult.second().equals(lottoPayment.second())) lottoWinCount++;
        if (lottoResult.third().equals(lottoPayment.third())) lottoWinCount++;
        if (lottoResult.fourth().equals(lottoPayment.fourth())) lottoWinCount++;
        if (lottoResult.fifth().equals(lottoPayment.fifth())) lottoWinCount++;
        if (lottoResult.sixth().equals(lottoPayment.sixth())) lottoWinCount++;
        return lottoWinCount;
    }

    @Override
    public LottoRank getRank(int countLottoNum) {
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
    }


}
