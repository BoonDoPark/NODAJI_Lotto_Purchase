package com.nodaji.lotto_payment.kafka.producer;

import com.nodaji.lotto_payment.domain.dto.request.KafkaLottoRankRequest;
import com.nodaji.lotto_payment.kafka.dto.KafkaStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaStatus<KafkaLottoRankRequest>> kafkaTemplate;


    public void send(KafkaLottoRankRequest kafkaLottoRankRequest, String topic) {
        KafkaStatus<KafkaLottoRankRequest> kafkaStatus = new KafkaStatus<>(kafkaLottoRankRequest, "result");
        log.info("{} 보내는 데이터 {}", topic, kafkaLottoRankRequest);
        kafkaTemplate.send(topic, kafkaStatus);
    }
}
