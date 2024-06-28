package com.nodaji.lotto_payment.domain.repository;

import com.nodaji.lotto_payment.domain.entity.TotalPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalPointRepository extends JpaRepository<TotalPoint, Long> {
    TotalPoint findByRound(Long round);
}
