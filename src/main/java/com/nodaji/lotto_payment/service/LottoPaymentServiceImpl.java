package com.nodaji.lotto_payment.service;

import com.nodaji.lotto_payment.domain.dto.request.LottoPaymentRequest;
import com.nodaji.lotto_payment.domain.dto.response.LottoPaymentResponse;
import com.nodaji.lotto_payment.domain.entity.LottoPayment;
import com.nodaji.lotto_payment.domain.entity.LottoResult;
import com.nodaji.lotto_payment.domain.repository.LottoPaymentRepository;
import com.nodaji.lotto_payment.domain.repository.LottoResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LottoPaymentServiceImpl implements LottoPaymentService{
    private final LottoPaymentRepository lottoPaymentRepository;
    private final LottoResultRepository lottoResultRepository;

    @Override
    public void save(List<LottoPaymentRequest> requests) {
        if (requests.isEmpty()) throw new IllegalArgumentException();
//        Long round = lottoResultRepository.findLastRow();
        Long finalRound = getRound();
        requests.forEach(req -> {
                    lottoPaymentRepository.save(req.toEntity(finalRound));
                    // id, createAt, userId, round 구매내역으로 전달 예정
                }
        );
    }

    private Long getRound() {
        Optional<LottoResult> optionalResult = Optional.ofNullable(lottoResultRepository.findLastRow());
        if (optionalResult.isPresent() && optionalResult.get().getId() != null) {
            return optionalResult.get().getId();
        } else {
            return 1L; // 테이블이 비어있을 경우 기본값으로 1을 반환하거나, 필요에 따라 다른 값을 반환할 수 있음
        }
    }

    @Override
    public List<LottoPaymentResponse> getAllByUserIdAndRoundId(Long round) {
        // 유저 id 필요
        return lottoPaymentRepository.findByRound(round);
    }

}
