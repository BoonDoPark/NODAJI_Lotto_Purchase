package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.config.utils.KafkaProducer;
import com.nodaji.lotto_payment.domain.dto.request.KafkaLottoRankRequest;
import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankResponse;
import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import com.nodaji.lotto_payment.config.utils.LottoRank;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
    private final LottoResultRepository lottoResultRepository;
    private final LottoPaymentRepository lottoPaymentRepository;
    private final KafkaProducer kafkaProducer;

    @Override
    public void save(LottoResultRequest req) {
        if (req == null) throw new IllegalArgumentException();
        LottoResult lottoResult = lottoResultRepository.save(req.toEntity());
        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findByRound(lottoResult.getId());
        Map<String, Integer> responseRank = new HashMap<>();
        LottoRankResponse lottoRankResponse = new LottoRankResponse();

        lottoPaymentResponses.forEach(lottoPaymentResponse -> {
            int count = lottoPaymentResponse.countLottoNumber(lottoPaymentResponse, req, lottoResult);
            boolean isBonusCheck = lottoPaymentResponse.isBonusNumber(lottoPaymentResponse, req.bonus());
            LottoRank lottoRank = LottoRank.getRank(count, isBonusCheck);
            System.out.println(lottoRank.getLottoRank());
            // 유저 ID, round, 등수 구매 내역으로 전달
//            lottoRankResponse.makeMaxRankByUser(lottoPaymentResponse.userId(), lottoRank.getLottoRank());


            responseRank.merge(lottoPaymentResponse.userId(), lottoRank.getLottoRank(), Math::min);
        });

        // auth에게 데이터 전달
        responseRank.forEach(((userId, rank) -> {
            KafkaLottoRankRequest kafkaLottoRankRequest = new KafkaLottoRankRequest(userId, "asd", rank, "lotto", "pbd0416@gmail.com");
            kafkaProducer.send(kafkaLottoRankRequest, "email-topic");
        }));
        // 유저 ID, 최고 등수를 구매 내역으로 전달 로직 필요

    }
}
