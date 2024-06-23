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
import java.util.UUID;

@Service
@AllArgsConstructor
public class LottoPaymentServiceImpl implements LottoPaymentService{
    private final LottoPaymentRepository lottoPaymentRepository;
    private final LottoResultRepository lottoResultRepository;

    @Override
    public void save(List<LottoPaymentRequest> requests) {
        if (requests.isEmpty()) throw new IllegalArgumentException();
//        Long round = lottoResultRepository.findLastRow().getId();
        Long finalRound = getRound();
        requests.forEach(req -> {
                    lottoPaymentRepository.save(req.toEntity(finalRound));
                    // id, createAt, userId, round 구매내역으로 전달 예정
                }
        );
    }

    private Long getRound() {
        LottoResult lottoResult = lottoResultRepository.findLastRow();
        if(lottoResult != null) return lottoResult.getId();
        else return 1L;
    }

    @Override
    public List<LottoPaymentResponse> getAllByUserIdAndRoundId(Long round) {
        return lottoPaymentRepository.findByRound(round);
    }

}
