package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.config.apis.ApiPoint;
import com.nodaji.lotto_payment.config.utils.LottoPoint;
import com.nodaji.lotto_payment.config.utils.LottoRank;
import com.nodaji.lotto_payment.kafka.dto.KafkaLottoHistoryRequest;
import com.nodaji.lotto_payment.domain.dto.request.KafkaLottoRankRequest;
import com.nodaji.lotto_payment.domain.dto.request.PointRequest;
import com.nodaji.lotto_payment.domain.repository.TotalPointRepository;
import com.nodaji.lotto_payment.kafka.producer.KafkaProducer;
import com.nodaji.lotto_payment.domain.dto.request.LottoResultRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
    private final LottoResultRepository lottoResultRepository;
    private final LottoPaymentRepository lottoPaymentRepository;
    private final TotalPointRepository totalPointRepository;
    private final KafkaProducer kafkaProducer;
    private final ApiPoint apiPoint;

    @Override
    public void save(LottoResultRequest req) {
        if (req == null) throw new IllegalArgumentException();
        LottoResult lottoResult = lottoResultRepository.save(req.toEntity());
        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findByRound(lottoResult.getId());
        Map<String, Integer> responseRank = new HashMap<>();
        Map<Integer, List<String>> lottoResultPoint = new HashMap<>();
        List<String> userList = new ArrayList<>();

        for(LottoPaymentResponse lottoPaymentResponse : lottoPaymentResponses) {
            int count = lottoPaymentResponse.countLottoNumber(lottoPaymentResponse, req, lottoResult);
            boolean isBonusCheck = lottoPaymentResponse.isBonusNumber(lottoPaymentResponse, req.bonus());
            LottoRank lottoRank = LottoRank.getRank(count, isBonusCheck);

            if (lottoRank == null) continue;
            userList.add(lottoPaymentResponse.userId());
            lottoResultPoint.put(lottoRank.getLottoRank(), userList);

            responseRank.put(lottoPaymentResponse.userId(), lottoRank.getLottoRank());
        }

        Long totalPoint = totalPointRepository.findByRound(lottoResult.getId()).getTotalPoint();
        Long fifthTotalPoint = lottoResultPoint.getOrDefault(5, new ArrayList<>()).size() * LottoPoint.FIFTH.getLottoPoint();
        Long fourthTotalPoint = lottoResultPoint.getOrDefault(4, new ArrayList<>()).size() * LottoPoint.FOURTH.getLottoPoint();
        long asd = totalPoint - (fifthTotalPoint + fourthTotalPoint);
        long thirdTotalPoint = (long) (asd * 0.12);
        long secondTotalPoint = (long) (asd* 0.13);
        long firstTotalPoint = (long) (asd * 0.75);

        lottoResultPoint.getOrDefault(5, new ArrayList<>()).forEach(userId -> {
            PointRequest pointRequest = new PointRequest(userId, LottoPoint.FIFTH.getLottoPoint());
            System.out.println(pointRequest.amount());
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(),5L);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest,"history-topic");
        });

        lottoResultPoint.getOrDefault(4, new ArrayList<>()).forEach(userId -> {
            PointRequest pointRequest = new PointRequest(userId, LottoPoint.FOURTH.getLottoPoint());
            System.out.println(pointRequest.amount());
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(),4L);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest,"history-topic");
//            apiPoint.sendPoint(pointRequest);
            //            // 유저 ID를 통해 등수, 당첨금을 구매 내역으로 전달 로직 필요 kafka
            // KafkaSendHistory kafkaSendhistory = new KafkaSendhistory(pointRequest.usdrId(), pointRequest.amount(), 4)
            // kafkaProducer.sendHistroy(kafkaSendhistory);0
//            // 유저 ID를 통해 등수, 당첨금을 구매 내역으로 전달 로직 필요 feign
        });

        lottoResultPoint.getOrDefault(3, new ArrayList<>()).forEach(userId -> {
            Long thirdPoint = thirdTotalPoint / lottoResultPoint.get(3).size();
            PointRequest pointRequest = new PointRequest(userId, thirdPoint);
            System.out.println(pointRequest.amount());
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(),3L);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest,"history-topic");
        });

        lottoResultPoint.getOrDefault(2, new ArrayList<>()).forEach(userId -> {
            Long secondPoint = secondTotalPoint / lottoResultPoint.get(2).size();
            PointRequest pointRequest = new PointRequest(userId, secondPoint);
            System.out.println(pointRequest.amount());
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(),2L);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest,"history-topic");
//            apiPoint.sendPoint(pointRequest);
            //            // 유저 ID를 통해 등수, 당첨금을 구매 내역으로 전달 로직 필요 kafka


//            // 유저 ID를 통해 등수, 당첨금을 구매 내역으로 전달 로직 필요 feign
        });

        lottoResultPoint.getOrDefault(1, new ArrayList<>()).forEach(userId -> {
            Long firstPoint = firstTotalPoint / lottoResultPoint.get(1).size();
            PointRequest pointRequest = new PointRequest(userId, firstPoint);
            System.out.println(pointRequest.amount());
//            apiPoint.sendPoint(pointRequest);
            //            // 유저 ID를 통해 등수, 당첨금을 구매 내역으로 전달 로직 필요 kafka
            // KafkaSendHistory kafkaSendhistory = new KafkaSendhistory(pointRequest.usdrId(), pointRequest.amount(), 4)
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(),1L);
            // kafkaProducer.sendHistroy(kafkaSendhistory);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest,"history-topic");


//            // 유저 ID를 통해 등수, 당첨금을 구매 내역으로 전달 로직 필요 feign
        });

        // auth에게 데이터 전달
        responseRank.forEach(((userId, rank) -> {
            KafkaLottoRankRequest kafkaLottoRankRequest = new KafkaLottoRankRequest(userId,"lotto", lottoResult.getId(), rank);
            kafkaProducer.send(kafkaLottoRankRequest, "email-topic");
        }));
    }
}
