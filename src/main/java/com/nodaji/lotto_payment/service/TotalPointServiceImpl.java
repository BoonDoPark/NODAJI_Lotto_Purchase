package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.config.apis.ApiPoint;
import com.nodaji.lotto_payment.config.utils.LottoPoint;
import com.nodaji.lotto_payment.domain.dto.request.PointRequest;
import com.nodaji.lotto_payment.domain.repository.TotalPointRepository;
import com.nodaji.lotto_payment.kafka.dto.KafkaLottoHistoryRequest;
import com.nodaji.lotto_payment.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TotalPointServiceImpl implements TotalPointService {
    private final TotalPointRepository totalPointRepository;
    private final ApiPoint apiPoint;
    private final KafkaProducer kafkaProducer;


    @Override
    public void matchRank(Map<Integer, List<String>> lottoResultPoint, int rank, Long totalPoint, Long lottoPoint) {
//        lottoResultPoint.getOrDefault(rank, new ArrayList<>()).forEach(userId -> {
//            Long pointAmount = (rank <= 3) ? totalPoint / lottoResultPoint.get(rank).size() : lottoPoint;
//
//            System.out.println(pointRequest.amount());
//
//            // 결제 서버에 포인트 전달
//            apiPoint.sendPoint(pointRequest);
//
//            // 구매 내역에 당첨금, 등수 전달
//            KafkaLottoHistoryRequest kafkaLottoHistoryRequest =
//                    new KafkaLottoHistoryRequest(pointRequest.userId(), pointRequest.amount(),5L);
//            kafkaProducer.sendHistory(kafkaLottoHistoryRequest,"history-topic");
//        });
    }
}
