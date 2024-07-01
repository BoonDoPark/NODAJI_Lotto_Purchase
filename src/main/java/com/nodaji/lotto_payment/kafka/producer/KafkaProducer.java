package com.nodaji.lotto_payment.kafka.producer;

import com.nodaji.lotto_payment.kafka.dto.KafkaLottoHistoryRequest;
import com.nodaji.lotto_payment.domain.dto.request.KafkaLottoRankRequest;
import com.nodaji.lotto_payment.domain.dto.request.KafkaPayInfoRequest;
import com.nodaji.lotto_payment.kafka.dto.KafkaPayStatus;
import com.nodaji.lotto_payment.kafka.dto.KafkaStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaStatus<KafkaLottoRankRequest>> kafkaTemplate;
    private final KafkaTemplate<String, KafkaPayStatus<KafkaPayInfoRequest>> kafkaPayTemplate;
    private final KafkaTemplate<String, KafkaStatus<KafkaLottoHistoryRequest>> kafkaHistoryTemplate;
    @Value("${kafka.product.name}") private String name;

    @Bean
    private NewTopic newTopic(){
        return new NewTopic("history-topic", 1, (short) 1);
    }

    public void send(KafkaLottoRankRequest kafkaLottoRankRequest, String topic) {
        KafkaStatus<KafkaLottoRankRequest> kafkaStatus = new KafkaStatus<>(kafkaLottoRankRequest, "result");
        log.info("{} 보내는 데이터 {}", topic, kafkaLottoRankRequest);
        kafkaTemplate.send(topic, kafkaStatus);
    }

    public void sendPay(KafkaPayInfoRequest kafkaPayInfoRequest, String topic) {
        KafkaPayStatus<KafkaPayInfoRequest> kafkaPayInfo = new KafkaPayStatus<>(kafkaPayInfoRequest, "pay");
        log.info("topic : {} data : {}", topic, kafkaPayInfoRequest);
        kafkaPayTemplate.send(topic, kafkaPayInfo);
    }
  
    public void sendHistory(KafkaLottoHistoryRequest kafkaLottoHistoryRequest, String topic) {
        KafkaStatus<KafkaLottoHistoryRequest> kafkaStatus = new KafkaStatus<>(kafkaLottoHistoryRequest,"history");
        log.info("topic : {} data : {}", topic, kafkaLottoHistoryRequest);
        kafkaHistoryTemplate.send(topic, kafkaStatus);
    }
}
