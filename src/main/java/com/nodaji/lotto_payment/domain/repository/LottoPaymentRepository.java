package com.nodaji.lotto_payment.domain.repository;

import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LottoPaymentRepository extends JpaRepository<LottoPayment, Long> {
//    @Query("select lt from lotto_tickets lt where round = :round")
//    List<LottoPaymentResponse> findAllRound(@Param("round") Long round);
    List<LottoPaymentResponse> findByRound(Long round);
}
