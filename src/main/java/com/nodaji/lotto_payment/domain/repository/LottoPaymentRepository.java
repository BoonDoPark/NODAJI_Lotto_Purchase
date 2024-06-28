package com.nodaji.lotto_payment.domain.repository;

import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LottoPaymentRepository extends JpaRepository<LottoPayment, Long> {

    List<LottoPaymentResponse> findByRound(Long round);

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE lotto_tickets SET rank = "
//            + "(SELECT CASE "
//            + "WHEN sub.matchCount = 6 THEN 1 "
//            + "WHEN sub.matchCount = 5 AND bonusMatch = 1 THEN 2 "
//            + "WHEN sub.matchCount = 5 THEN 3 "
//            + "WHEN sub.matchCount = 4 THEN 4 "
//            + "WHEN sub.matchCount = 3 THEN 5 "
//            + "ELSE 6 END "
//            + "FROM ("
//            + "  SELECT "
//            + "    (CASE WHEN first IN (lr.first, lr.second, lr.third, lr.fourth, lr.fifth, lr.sixth) THEN 1 ELSE 0 END) + "
//            + "    (CASE WHEN second IN (lr.first, lr.second, lr.third, lr.fourth, lr.fifth, lr.sixth) THEN 1 ELSE 0 END) + "
//            + "    (CASE WHEN third IN (lr.first, lr.second, lr.third, lr.fourth, lr.fifth, lr.sixth) THEN 1 ELSE 0 END) + "
//            + "    (CASE WHEN fourth IN (lr.first, lr.second, lr.third, lr.fourth, lr.fifth, lr.sixth) THEN 1 ELSE 0 END) + "
//            + "    (CASE WHEN fifth IN (lr.first, lr.second, lr.third, lr.fourth, lr.fifth, lr.sixth) THEN 1 ELSE 0 END) + "
//            + "    (CASE WHEN sixth IN (lr.first, lr.second, lr.third, lr.fourth, lr.fifth, lr.sixth) THEN 1 ELSE 0 END) AS matchCount, "
//            + "    CASE WHEN lr.bonus IN (first, second, third, fourth, fifth, sixth) THEN 1 ELSE 0 END AS bonusMatch "
//            + "  FROM lotto_result as lr "
//            + "  WHERE lr.id = :round "
//            + ") AS sub) "
//            + "WHERE u.userId = :userId")
//    void updateLottoRank(Long userId, Long drawId);

}
