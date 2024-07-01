package com.nodaji.lotto_payment.domain.repository;

import com.nodaji.lotto_payment.domain.dto.response.LottoRankPointResponse;
import com.nodaji.lotto_payment.domain.entity.LottoRankPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LottoRankPointRepository extends JpaRepository<LottoRankPoint, Long> {
    List<LottoRankPointResponse> findByRank(Integer rank);
    LottoRankPoint findByRankAndPayId(Integer rank, Long payId);
}
