package com.nodaji.lotto_payment.domain.repository;

import com.nodaji.lotto_payment.domain.entity.LottoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LottoResultRepository extends JpaRepository<LottoResult, Long> {
    @Query("select * from lotto_result order by id desc limit 1")
    LottoResult findLastRow();
}
