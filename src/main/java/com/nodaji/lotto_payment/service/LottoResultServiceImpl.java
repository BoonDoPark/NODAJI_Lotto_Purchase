package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.config.apis.ApiPoint;
import com.nodaji.lotto_payment.config.utils.LottoPoint;
import com.nodaji.lotto_payment.config.utils.LottoRank;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;
import com.nodaji.lotto_payment.domain.entity.LottoRankPoint;
import com.nodaji.lotto_payment.domain.repository.LottoRankPointRepository;
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
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service
@RequiredArgsConstructor
public class LottoResultServiceImpl implements LottoResultService {
    private final LottoResultRepository lottoResultRepository;
    private final LottoPaymentRepository lottoPaymentRepository;
    private final TotalPointRepository totalPointRepository;
    private final LottoRankPointRepository lottoRankPointRepository;
    private final KafkaProducer kafkaProducer;
    private final ApiPoint apiPoint;

    @Override
    @Transactional
    public void save(LottoResultRequest req) {
//        if (req == null) throw new IllegalArgumentException();
//        LottoResult lottoResult = lottoResultRepository.save(req.toEntity());
//        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findByRound(lottoResult.getId());
//        Map<String, Integer> responseRank = new HashMap<>();
//
//        for (LottoPaymentResponse lottoPaymentResponse : lottoPaymentResponses) {
//            System.out.println(lottoPaymentResponse.toString());
//            int count = lottoPaymentResponse.countLottoNumber(lottoPaymentResponse, req, lottoResult);
//            boolean isBonusCheck = lottoPaymentResponse.isBonusNumber(lottoPaymentResponse, req.bonus());
//            LottoRank lottoRank = LottoRank.getRank(count, isBonusCheck);
//            if (lottoRank == null) continue;
//
//            lottoRankPointRepository.save(LottoRankPoint.builder()
//                    .payId(lottoPaymentResponse.id())
//                    .userId(lottoPaymentResponse.userId())
//                    .round(lottoResult.getId())
//                    .rank(lottoRank.getLottoRank())
//                    .build());
//            System.out.println(lottoRank.getLottoRank());
//            responseRank.put(lottoPaymentResponse.userId(), lottoRank.getLottoRank());
//        }
//
//        Long totalPoint = totalPointRepository.findByRound(lottoResult.getId()).getTotalPoint();
//        if (totalPoint == null) throw new IllegalArgumentException("Not Total Point");
//
//        List<LottoRankPointResponse> fifthRankPointResponses = lottoRankPointRepository.findByRank(5);
//        for (LottoRankPointResponse lottoRankPointResponse : fifthRankPointResponses) {
//            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), LottoPoint.FIFTH.getLottoPoint());
////            apiPoint.sendPoint(pointRequest);
//            LottoRankPoint lottoRankPoint = new LottoRankPoint();
//            lottoRankPoint.setPoint(pointRequest.amount());
//            lottoRankPointRepository.save(lottoRankPoint);
////            System.out.println(pointRequest.amount());
//
//            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
//                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(), 5L);
//            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
//        }
//        Long fifthTotalPoint = fifthRankPointResponses.size() * LottoPoint.FIFTH.getLottoPoint();
//
//        List<LottoRankPointResponse> fourthRankPointResponses = lottoRankPointRepository.findByRank(4);
//        for (LottoRankPointResponse lottoRankPointResponse : fourthRankPointResponses) {
//            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), LottoPoint.FIFTH.getLottoPoint());
////            apiPoint.sendPoint(pointRequest);
//            LottoRankPoint lottoRankPoint = new LottoRankPoint();
//            lottoRankPoint.setPoint(pointRequest.amount());
//            lottoRankPointRepository.save(lottoRankPoint);
////            System.out.println(pointRequest.amount());
//
//            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
//                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(), 4L);
//            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
//        }
//        Long fourthTotalPoint = fifthRankPointResponses.size() * LottoPoint.FOURTH.getLottoPoint();
//        long remainPoint = totalPoint - (fifthTotalPoint + fourthTotalPoint);
//        long thirdTotalPoint = (long) (remainPoint * 0.12);
//        long secondTotalPoint = (long) (remainPoint * 0.13);
//        long firstTotalPoint = (long) (remainPoint * 0.75);
//
//        List<LottoRankPointResponse> thirdRankPointResponses = lottoRankPointRepository.findByRank(3);
//        for (LottoRankPointResponse lottoRankPointResponse : thirdRankPointResponses) {
//            long thirdRankPoint = thirdTotalPoint / thirdRankPointResponses.size();
//            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), thirdRankPoint);
////            apiPoint.sendPoint(pointRequest);
//            LottoRankPoint lottoRankPoint = new LottoRankPoint();
//            lottoRankPoint.setPoint(thirdRankPoint);
//            lottoRankPointRepository.save(lottoRankPoint);
////            System.out.println(pointRequest.amount());
//
//            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
//                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(), 3L);
//            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
//        }
//
//        List<LottoRankPointResponse> secondRankPointResponses = lottoRankPointRepository.findByRank(2);
//        for (LottoRankPointResponse lottoRankPointResponse : secondRankPointResponses) {
//            long secondRankPoint = secondTotalPoint / secondRankPointResponses.size();
//            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), secondRankPoint);
////            apiPoint.sendPoint(pointRequest);
//            LottoRankPoint lottoRankPoint = new LottoRankPoint();
//            lottoRankPoint.setPoint(secondRankPoint);
//            lottoRankPointRepository.save(lottoRankPoint);
////            System.out.println(pointRequest.amount());
//
//            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
//                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(), 2L);
//            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
//        }
//
//        List<LottoRankPointResponse> firstRankPointResponses = lottoRankPointRepository.findByRank(1);
//        for (LottoRankPointResponse lottoRankPointResponse : firstRankPointResponses) {
//            long firstRankPoint = firstTotalPoint / firstRankPointResponses.size();
//            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), firstRankPoint);
////            apiPoint.sendPoint(pointRequest);
//            LottoRankPoint lottoRankPoint = new LottoRankPoint();
//            lottoRankPoint.setPoint(firstRankPoint);
//            lottoRankPointRepository.save(lottoRankPoint);
//            System.out.println(pointRequest.amount());
//
//            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
//                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(), 1L);
//            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
//        }
//
//        // auth에게 데이터 전달
//        responseRank.forEach(((userId, rank) -> {
//            KafkaLottoRankRequest kafkaLottoRankRequest = new KafkaLottoRankRequest(userId, "lotto", lottoResult.getId(), rank);
//            kafkaProducer.send(kafkaLottoRankRequest, "email-topic");
//        }));


        if (req == null) throw new IllegalArgumentException();

        LottoResult lottoResult = lottoResultRepository.save(req.toEntity());
        List<LottoPaymentResponse> lottoPaymentResponses = lottoPaymentRepository.findByRound(lottoResult.getId());
        Map<String, Integer> responseRank = processLottoPayments(lottoPaymentResponses, req, lottoResult);

        Long totalPoint = totalPointRepository.findByRound(lottoResult.getId()).getTotalPoint();
        if (totalPoint == null) throw new IllegalArgumentException("Not Total Point");

        distributePoints(totalPoint, lottoRankPointRepository);
        notifyAuth(responseRank, lottoResult.getId());
    }

    private Map<String, Integer> processLottoPayments(List<LottoPaymentResponse> lottoPaymentResponses, LottoResultRequest req, LottoResult lottoResult) {
        Map<String, Integer> responseRank = new HashMap<>();

        for (LottoPaymentResponse lottoPaymentResponse : lottoPaymentResponses) {
            int count = lottoPaymentResponse.countLottoNumber(lottoPaymentResponse, req, lottoResult);
            boolean isBonusCheck = lottoPaymentResponse.isBonusNumber(lottoPaymentResponse, req.bonus());
            LottoRank lottoRank = LottoRank.getRank(count, isBonusCheck);
            if (lottoRank == null) {
                lottoRankPointRepository.save(LottoRankPoint.builder()
                        .payId(lottoPaymentResponse.id())
                        .userId(lottoPaymentResponse.userId())
                        .round(lottoResult.getId())
                        .build());
                continue;
            }
            lottoRankPointRepository.save(LottoRankPoint.builder()
                    .payId(lottoPaymentResponse.id())
                    .userId(lottoPaymentResponse.userId())
                    .round(lottoResult.getId())
                    .rank(lottoRank.getLottoRank()).build());
            responseRank.put(lottoPaymentResponse.userId(), lottoRank.getLottoRank());
        }

        return responseRank;
    }

    private void distributePoints(Long totalPoint, LottoRankPointRepository lottoRankPointRepository) {
        List<LottoRankPointResponse> rankPointResponses;

        // 5등 포인트 분배
        rankPointResponses = lottoRankPointRepository.findByRank(5);
        distributeFixedPoints(rankPointResponses, LottoPoint.FIFTH.getLottoPoint(), 5);
        totalPoint -= rankPointResponses.size() * LottoPoint.FIFTH.getLottoPoint();

        // 4등 포인트 분배
        rankPointResponses = lottoRankPointRepository.findByRank(4);
        distributeFixedPoints(rankPointResponses, LottoPoint.FOURTH.getLottoPoint(), 4); // Assuming 4th place points are the same as 5th place
        totalPoint -= rankPointResponses.size() * LottoPoint.FOURTH.getLottoPoint();

        // 남은 포인트 계산
        long thirdTotalPoint = (long) (totalPoint * 0.12);
        long secondTotalPoint = (long) (totalPoint * 0.13);
        long firstTotalPoint = totalPoint - (thirdTotalPoint + secondTotalPoint); // 나머지 포인트 모두 1등에 할당

        // 3등 포인트 분배
        rankPointResponses = lottoRankPointRepository.findByRank(3);
        distributeVariablePoints(rankPointResponses, thirdTotalPoint, 3);

        // 2등 포인트 분배
        rankPointResponses = lottoRankPointRepository.findByRank(2);
        distributeVariablePoints(rankPointResponses, secondTotalPoint, 2);

        // 1등 포인트 분배
        rankPointResponses = lottoRankPointRepository.findByRank(1);
        distributeVariablePoints(rankPointResponses, firstTotalPoint, 1);
    }

    private void distributeFixedPoints(List<LottoRankPointResponse> rankPointResponses, long point, int rank) {
        for (LottoRankPointResponse lottoRankPointResponse : rankPointResponses) {
            // 결제 서버
            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), point);

            // 구매 내역 서버
            LottoRankPoint lottoRankPoint = lottoRankPointRepository.findByRankAndPayId(rank, lottoRankPointResponse.payId());
            lottoRankPoint.setPoint(pointRequest.amount());
            lottoRankPointRepository.save(lottoRankPoint);
            System.out.println(pointRequest.amount());

            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(lottoRankPointResponse.payId(), pointRequest.amount(), rank);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "update-topic");
        }
    }

    private void distributeVariablePoints(List<LottoRankPointResponse> rankPointResponses, long totalPoints, int rank) {
        if (rankPointResponses.isEmpty()) return;
        long pointPerUser = totalPoints / rankPointResponses.size();

        for (LottoRankPointResponse lottoRankPointResponse : rankPointResponses) {
            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), pointPerUser);
            LottoRankPoint lottoRankPoint = new LottoRankPoint();
            lottoRankPoint.setPoint(pointRequest.amount());
            lottoRankPointRepository.save(lottoRankPoint);
            System.out.println(pointRequest.amount());

            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(lottoRankPointResponse.payId(), pointRequest.amount(), rank);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "update-topic");
        }
    }

    private void notifyAuth(Map<String, Integer> responseRank, Long round) {
        responseRank.forEach((userId, rank) -> {
            KafkaLottoRankRequest kafkaLottoRankRequest = new KafkaLottoRankRequest(userId, "lotto", round, rank);
            kafkaProducer.send(kafkaLottoRankRequest, "email-topic");
        });
    }
}
