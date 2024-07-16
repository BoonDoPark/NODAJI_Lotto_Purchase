package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dao.LottoRankPointDao;
import com.nodaji.lotto_payment.domain.dto.request.KafkaLottoRankRequest;
import com.nodaji.lotto_payment.domain.dto.request.PointRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;
import com.nodaji.lotto_payment.kafka.dto.KafkaLottoHistoryRequest;
import com.nodaji.lotto_payment.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final KafkaProducer kafkaProducer;
    private final LottoRankPointDao lottoRankPointDao;

    @Override
    public void notifyRankForAuth(Map<String, Integer> responseRank, Long round) {
        responseRank.forEach((userId, rank) -> {
            KafkaLottoRankRequest kafkaLottoRankRequest =
                    new KafkaLottoRankRequest(userId, "lotto", round, rank);
            kafkaProducer.send(kafkaLottoRankRequest, "email-topic");
        });
    }

    @Override
    public void notifyTopRankForUser(List<LottoRankPointResponse> rankPointResponses, long totalPoints, int rank) {
        if (rankPointResponses.isEmpty()) return;
        long pointPerUser = totalPoints / rankPointResponses.size();

        for (LottoRankPointResponse lottoRankPointResponse : rankPointResponses) {
            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), pointPerUser);
            lottoRankPointDao.savePoint(rank, pointRequest.amount(), lottoRankPointResponse.payId());

//            apiPoint.sendPoint(pointRequest);
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(lottoRankPointResponse.payId(), pointRequest.amount(), rank);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
        }
    }

    @Override
    public void notifyDownRankForUser(List<LottoRankPointResponse> rankPointResponses, long point, int rank) {
        if (rankPointResponses.isEmpty()) return;

        for (LottoRankPointResponse lottoRankPointResponse : rankPointResponses) {
            PointRequest pointRequest = new PointRequest(lottoRankPointResponse.userId(), point);
            lottoRankPointDao.savePoint(rank, pointRequest.amount(), lottoRankPointResponse.payId());

//            apiPoint.sendPoint(pointRequest);
            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
                    new KafkaLottoHistoryRequest(lottoRankPointResponse.payId(), pointRequest.amount(), rank);
            kafkaProducer.sendHistory(kafkaLottoHistoryRequest, "history-topic");
        }
    }
}
