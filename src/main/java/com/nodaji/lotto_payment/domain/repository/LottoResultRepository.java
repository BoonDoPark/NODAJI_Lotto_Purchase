package com.nodaji.lotto_payment.domain.repository;

import com.nodaji.lotto_payment.domain.entity.LottoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LottoResultRepository extends JpaRepository<LottoResult, Long> {
    @Query(value = "SELECT * FROM lotto_result ORDER BY id DESC LIMIT 1", nativeQuery = true)
    LottoResult findLastRow();
}
